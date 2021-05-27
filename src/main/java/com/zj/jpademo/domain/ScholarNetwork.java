package com.zj.jpademo.domain;

import com.google.gson.*;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ScholarNetwork
{
    private static final String TEST_HOSTNAME = "159.75.27.46";
    private static final int TEST_PORT = 9080;

    private static DgraphClient createDgraphClient(boolean withAuthHeader) {
        ManagedChannel channel =
                ManagedChannelBuilder.forAddress(TEST_HOSTNAME, TEST_PORT).usePlaintext().build();
        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);

        if (withAuthHeader) {
            Metadata metadata = new Metadata();
            metadata.put(
                    Metadata.Key.of("auth-token", Metadata.ASCII_STRING_MARSHALLER), "the-auth-token-value");
            stub = MetadataUtils.attachHeaders(stub, metadata);
        }

        return new DgraphClient(stub);
    }

    public JsonObject dgraphData(String personName) {
        DgraphClient dgraphClient = createDgraphClient(false);

        // Set schema
        String schema = "name: string @index(exact) .";
        DgraphProto.Operation operation = DgraphProto.Operation.newBuilder().setSchema(schema).build();
        dgraphClient.alter(operation);

        Transaction txn = dgraphClient.newTransaction();
        // Query
        String query =
                "query all($a: string) {\n" +
                        "      all(func: eq(name, $a))\n" +
                        "      {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        relation1\n" +
                        "        {\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        ~relation1\n" +
                        "        {\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        relation2\n" +
                        "        {\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        ~relation2\n" +
                        "        {\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }";
        Map<String, String> vars = Collections.singletonMap("$a", personName);
        DgraphProto.Response res = txn.queryWithVars(query, vars);

        // Deserialize
        JsonObject ppl = JsonParser.parseString(res.getJson().toStringUtf8()).getAsJsonObject();
        System.out.println(ppl);
        return ppl;
    }

    public JsonObject getData(String personName) {
        JsonArray rawinfo = dgraphData(personName).getAsJsonArray("all");
        System.out.println(rawinfo);

        JsonObject allInfo = new JsonObject();
        List<JsonObject> allNode = new ArrayList<>();
        List<JsonObject> allLink = new ArrayList<>();
        JsonObject nodeCenter = new JsonObject();

        nodeCenter.add("name", JsonParser.parseString(personName));
        nodeCenter.add("category", JsonParser.parseString("0"));
        allNode.add(nodeCenter);
        for (int pi = 0; pi < rawinfo.size(); pi++)
        {
            JsonObject info = rawinfo.get(pi).getAsJsonObject();
            if (info.has("relation1")) {
                JsonArray relatePersonArray = info.get("relation1").getAsJsonArray();
                for (int i = 0; i < relatePersonArray.size(); i++) {
                    JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                    if (!relatePerson.get("name").getAsString().equals(personName)) {
                        JsonObject nodeP = new JsonObject();
                        nodeP.add("name", relatePerson.get("name"));
                        nodeP.add("category", JsonParser.parseString("1"));
                        if (!allNode.contains(nodeP)) {
                            allNode.add(nodeP);
                        }

                        JsonObject linkP = new JsonObject();
                        linkP.add("source", JsonParser.parseString(personName));
                        linkP.add("target", relatePerson.get("name"));
                        linkP.add("value", JsonParser.parseString("学生"));
                        if (!allLink.contains(linkP)) {
                            allLink.add(linkP);
                        }
                    }
                }
            }
            if (info.has("~relation1")) {
                JsonArray relatePersonArray = info.get("~relation1").getAsJsonArray();
                for (int i = 0; i < relatePersonArray.size(); i++) {
                    JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                    if (!relatePerson.get("name").getAsString().equals(personName)) {
                        JsonObject nodeP = new JsonObject();
                        nodeP.add("name", relatePerson.get("name"));
                        nodeP.add("category", JsonParser.parseString("1"));
                        if (!allNode.contains(nodeP)) {
                            allNode.add(nodeP);
                        }

                        JsonObject linkP = new JsonObject();
                        linkP.add("source", JsonParser.parseString(personName));
                        linkP.add("target", relatePerson.get("name"));
                        linkP.add("value", JsonParser.parseString("导师"));
                        if (!allLink.contains(linkP)) {
                            allLink.add(linkP);
                        }
                    }
                }
            }

            if (info.has("relation2")) {
                JsonArray relatePersonArray = info.get("relation2").getAsJsonArray();
                for (int i = 0; i < relatePersonArray.size(); i++) {
                    JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                    if (!relatePerson.get("name").getAsString().equals(personName)) {
                        JsonObject nodeP = new JsonObject();
                        nodeP.add("name", relatePerson.get("name"));
                        nodeP.add("category", JsonParser.parseString("1"));
                        if (!allNode.contains(nodeP)) {
                            allNode.add(nodeP);
                        }

                        JsonObject linkP = new JsonObject();
                        linkP.add("source", JsonParser.parseString(personName));
                        linkP.add("target", relatePerson.get("name"));
                        linkP.add("value", JsonParser.parseString("合作"));
                        if (!allLink.contains(linkP)) {
                            allLink.add(linkP);
                        }
                    }
                }
            }
            if (info.has("~relation2")) {
                JsonArray relatePersonArray = info.get("~relation2").getAsJsonArray();
                for (int i = 0; i < relatePersonArray.size(); i++) {
                    JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                    if (!relatePerson.get("name").getAsString().equals(personName)) {
                        JsonObject nodeP = new JsonObject();
                        nodeP.add("name", relatePerson.get("name"));
                        nodeP.add("category", JsonParser.parseString("1"));
                        if (!allNode.contains(nodeP)) {
                            allNode.add(nodeP);
                        }

                        JsonObject linkP = new JsonObject();
                        linkP.add("source", JsonParser.parseString(personName));
                        linkP.add("target", relatePerson.get("name"));
                        linkP.add("value", JsonParser.parseString("合作"));
                        if (!allLink.contains(linkP)) {
                            allLink.add(linkP);
                        }
                    }
                }
            }

        }


        Gson gson = new Gson();
        allInfo.add("nodes", JsonParser.parseString(gson.toJson(allNode)));
        allInfo.add("links", JsonParser.parseString(gson.toJson(allLink)));
        return allInfo;
    }
    /*
    public static void main(String[] args) {
        App app = new App();
        JsonObject o = app.getData("诺罗布·阿勒坦呼亚格");
        String a = "yes";
    }
    */

    public JsonObject finddgraphDatabyid(String personID) {
        DgraphClient dgraphClient = createDgraphClient(false);

        // Set schema
        String schema = "name: string @index(exact) .";
        DgraphProto.Operation operation = DgraphProto.Operation.newBuilder().setSchema(schema).build();
        dgraphClient.alter(operation);

        Transaction txn = dgraphClient.newTransaction();
        // Query
        String query =
                "query all($a: string) {\n" +
                        "      all(func: eq(id, $a))\n" +
                        "      {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +

                        "        relation1\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        ~relation1\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        ~relation2\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        }\n" +

                        "        ~relation1\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        relation1\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        relation2\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        }\n" +

                        "        relation2\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        ~relation1\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        ~relation2\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        }\n" +

                        "        ~relation2\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        relation1\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        relation2\n" +
                        "        {\n" +
                        "        id\n" +
                        "        name\n" +
                        "        institute\n" +
                        "        }\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }";
        Map<String, String> vars = Collections.singletonMap("$a", personID);
        DgraphProto.Response res = txn.queryWithVars(query, vars);

        // Deserialize
        JsonObject ppl = JsonParser.parseString(res.getJson().toStringUtf8()).getAsJsonObject();
        System.out.println(ppl);
        return ppl;
    }
    public JsonObject getDataByID(String personID) {
        JsonArray rawinfo = finddgraphDatabyid(personID).getAsJsonArray("all");
        System.out.println(rawinfo);
        String personName = String.valueOf(rawinfo.get(0).getAsJsonObject().get("name"));

        JsonObject allInfo = new JsonObject();
        List<JsonObject> allNode = new ArrayList<>();
        List<JsonObject> allLink = new ArrayList<>();
        JsonObject nodeCenter = new JsonObject();

        nodeCenter.add("name", JsonParser.parseString(personName));
        nodeCenter.add("category", JsonParser.parseString("0"));
        allNode.add(nodeCenter);

        JsonObject info = rawinfo.get(0).getAsJsonObject();
            //relation1
            if (info.has("relation1"))
            {
                JsonArray relatePersonArray = info.get("relation1").getAsJsonArray();
                for (int i = 0; i < relatePersonArray.size(); i++)
                {
                    JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                    if (!relatePerson.get("id").getAsString().equals(personID))
                    {
                        JsonObject nodeP = new JsonObject();
                        nodeP.add("name", relatePerson.get("name"));
                        nodeP.add("category", JsonParser.parseString("1"));
                        if (!allNode.contains(nodeP)) {
                            allNode.add(nodeP);
                        }

                        JsonObject linkP = new JsonObject();
                        linkP.add("source", JsonParser.parseString(personName));
                        linkP.add("target", relatePerson.get("name"));
                        linkP.add("value", JsonParser.parseString("学生"));
                        if (!allLink.contains(linkP)) {
                            allLink.add(linkP);
                        }
                    }
                    if(relatePerson.has("~relation1"))
                    {
                        JsonArray relatePersonArray1 = relatePerson.get("~relation1").getAsJsonArray();
                        for (int i1 = 0; i1 < relatePersonArray1.size(); i1++) {
                            JsonObject relatePerson1 = relatePersonArray1.get(i1).getAsJsonObject();
                            if (!relatePerson1.get("id").getAsString().equals(personID)
                            && !relatePerson1.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                            {
                                JsonObject nodeP = new JsonObject();
                                nodeP.add("name", relatePerson1.get("name"));
                                nodeP.add("category", JsonParser.parseString("2"));
                                if (!allNode.contains(nodeP)) {
                                    allNode.add(nodeP);
                                }

                                JsonObject linkP = new JsonObject();
                                linkP.add("source", relatePerson.get("name"));
                                linkP.add("target", relatePerson1.get("name"));
                                linkP.add("value", JsonParser.parseString("导师"));
                                if (!allLink.contains(linkP)) {
                                    allLink.add(linkP);
                                }
                            }
                        }
                    }
                    if(relatePerson.has("~relation2"))
                    {
                        JsonArray relatePersonArray2 = relatePerson.get("~relation2").getAsJsonArray();
                        for (int i2 = 0; i2 < relatePersonArray2.size(); i2++) {
                            JsonObject relatePerson2 = relatePersonArray2.get(i2).getAsJsonObject();
                            if (!relatePerson2.get("id").getAsString().equals(personID)
                            && !relatePerson2.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                               {
                                JsonObject nodeP = new JsonObject();
                                nodeP.add("name", relatePerson2.get("name"));
                                nodeP.add("category", JsonParser.parseString("2"));
                                if (!allNode.contains(nodeP)) {
                                    allNode.add(nodeP);
                                }

                                JsonObject linkP = new JsonObject();
                                linkP.add("source", relatePerson.get("name"));
                                linkP.add("target", relatePerson2.get("name"));
                                linkP.add("value", JsonParser.parseString("合作"));
                                if (!allLink.contains(linkP)) {
                                    allLink.add(linkP);
                                }
                            }
                        }
                    }
                }
            }

            //~relation1
        if (info.has("~relation1"))
        {
            JsonArray relatePersonArray = info.get("~relation1").getAsJsonArray();
            for (int i = 0; i < relatePersonArray.size(); i++)
            {
                JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                if (!relatePerson.get("id").getAsString().equals(personID))
                {
                    JsonObject nodeP = new JsonObject();
                    nodeP.add("name", relatePerson.get("name"));
                    nodeP.add("category", JsonParser.parseString("1"));
                    if (!allNode.contains(nodeP)) {
                        allNode.add(nodeP);
                    }

                    JsonObject linkP = new JsonObject();
                    linkP.add("source", JsonParser.parseString(personName));
                    linkP.add("target", relatePerson.get("name"));
                    linkP.add("value", JsonParser.parseString("导师"));
                    if (!allLink.contains(linkP)) {
                        allLink.add(linkP);
                    }
                }
                if(relatePerson.has("relation1"))
                {
                    JsonArray relatePersonArray1 = relatePerson.get("relation1").getAsJsonArray();
                    for (int i1 = 0; i1 < relatePersonArray1.size(); i1++) {
                        JsonObject relatePerson1 = relatePersonArray1.get(i1).getAsJsonObject();
                        if (!relatePerson1.get("id").getAsString().equals(personID)
                                && !relatePerson1.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                        {
                            JsonObject nodeP = new JsonObject();
                            nodeP.add("name", relatePerson1.get("name"));
                            nodeP.add("category", JsonParser.parseString("2"));
                            if (!allNode.contains(nodeP)) {
                                allNode.add(nodeP);
                            }

                            JsonObject linkP = new JsonObject();
                            linkP.add("source", relatePerson.get("name"));
                            linkP.add("target", relatePerson1.get("name"));
                            linkP.add("value", JsonParser.parseString("学生"));
                            if (!allLink.contains(linkP)) {
                                allLink.add(linkP);
                            }
                        }
                    }
                }
                if(relatePerson.has("relation2"))
                {
                    JsonArray relatePersonArray2 = relatePerson.get("relation2").getAsJsonArray();
                    for (int i2 = 0; i2 < relatePersonArray2.size(); i2++) {
                        JsonObject relatePerson2 = relatePersonArray2.get(i2).getAsJsonObject();
                        if (!relatePerson2.get("id").getAsString().equals(personID)
                                && !relatePerson2.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                        {
                            JsonObject nodeP = new JsonObject();
                            nodeP.add("name", relatePerson2.get("name"));
                            nodeP.add("category", JsonParser.parseString("2"));
                            if (!allNode.contains(nodeP)) {
                                allNode.add(nodeP);
                            }

                            JsonObject linkP = new JsonObject();
                            linkP.add("source", relatePerson.get("name"));
                            linkP.add("target", relatePerson2.get("name"));
                            linkP.add("value", JsonParser.parseString("合作"));
                            if (!allLink.contains(linkP)) {
                                allLink.add(linkP);
                            }
                        }
                    }
                }
            }
        }
        //relation2
        if (info.has("relation2"))
        {
            JsonArray relatePersonArray = info.get("relation2").getAsJsonArray();
            for (int i = 0; i < relatePersonArray.size(); i++)
            {
                JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                if (!relatePerson.get("id").getAsString().equals(personID))
                {
                    JsonObject nodeP = new JsonObject();
                    nodeP.add("name", relatePerson.get("name"));
                    nodeP.add("category", JsonParser.parseString("1"));
                    if (!allNode.contains(nodeP)) {
                        allNode.add(nodeP);
                    }

                    JsonObject linkP = new JsonObject();
                    linkP.add("source", JsonParser.parseString(personName));
                    linkP.add("target", relatePerson.get("name"));
                    linkP.add("value", JsonParser.parseString("合作"));
                    if (!allLink.contains(linkP)) {
                        allLink.add(linkP);
                    }
                }
                if(relatePerson.has("~relation1"))
                {
                    JsonArray relatePersonArray1 = relatePerson.get("~relation1").getAsJsonArray();
                    for (int i1 = 0; i1 < relatePersonArray1.size(); i1++) {
                        JsonObject relatePerson1 = relatePersonArray1.get(i1).getAsJsonObject();
                        if (!relatePerson1.get("id").getAsString().equals(personID)
                                && !relatePerson1.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                        {
                            JsonObject nodeP = new JsonObject();
                            nodeP.add("name", relatePerson1.get("name"));
                            nodeP.add("category", JsonParser.parseString("2"));
                            if (!allNode.contains(nodeP)) {
                                allNode.add(nodeP);
                            }

                            JsonObject linkP = new JsonObject();
                            linkP.add("source", relatePerson.get("name"));
                            linkP.add("target", relatePerson1.get("name"));
                            linkP.add("value", JsonParser.parseString("导师"));
                            if (!allLink.contains(linkP)) {
                                allLink.add(linkP);
                            }
                        }
                    }
                }
                if(relatePerson.has("~relation2"))
                {
                    JsonArray relatePersonArray2 = relatePerson.get("~relation2").getAsJsonArray();
                    for (int i2 = 0; i2 < relatePersonArray2.size(); i2++) {
                        JsonObject relatePerson2 = relatePersonArray2.get(i2).getAsJsonObject();
                        if (!relatePerson2.get("id").getAsString().equals(personID)
                                && !relatePerson2.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                        {
                            JsonObject nodeP = new JsonObject();
                            nodeP.add("name", relatePerson2.get("name"));
                            nodeP.add("category", JsonParser.parseString("2"));
                            if (!allNode.contains(nodeP)) {
                                allNode.add(nodeP);
                            }

                            JsonObject linkP = new JsonObject();
                            linkP.add("source", relatePerson.get("name"));
                            linkP.add("target", relatePerson2.get("name"));
                            linkP.add("value", JsonParser.parseString("合作"));
                            if (!allLink.contains(linkP)) {
                                allLink.add(linkP);
                            }
                        }
                    }
                }
            }
        }

        //~relation2
        if (info.has("~relation2"))
        {
            JsonArray relatePersonArray = info.get("~relation2").getAsJsonArray();
            for (int i = 0; i < relatePersonArray.size(); i++)
            {
                JsonObject relatePerson = relatePersonArray.get(i).getAsJsonObject();
                if (!relatePerson.get("id").getAsString().equals(personID))
                {
                    JsonObject nodeP = new JsonObject();
                    nodeP.add("name", relatePerson.get("name"));
                    nodeP.add("category", JsonParser.parseString("1"));
                    if (!allNode.contains(nodeP)) {
                        allNode.add(nodeP);
                    }

                    JsonObject linkP = new JsonObject();
                    linkP.add("source", JsonParser.parseString(personName));
                    linkP.add("target", relatePerson.get("name"));
                    linkP.add("value", JsonParser.parseString("合作"));
                    if (!allLink.contains(linkP)) {
                        allLink.add(linkP);
                    }
                }
                if(relatePerson.has("relation1"))
                {
                    JsonArray relatePersonArray1 = relatePerson.get("relation1").getAsJsonArray();
                    for (int i1 = 0; i1 < relatePersonArray1.size(); i1++) {
                        JsonObject relatePerson1 = relatePersonArray1.get(i1).getAsJsonObject();
                        if (!relatePerson1.get("id").getAsString().equals(personID)
                                && !relatePerson1.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                        {
                            JsonObject nodeP = new JsonObject();
                            nodeP.add("name", relatePerson1.get("name"));
                            nodeP.add("category", JsonParser.parseString("2"));
                            if (!allNode.contains(nodeP)) {
                                allNode.add(nodeP);
                            }

                            JsonObject linkP = new JsonObject();
                            linkP.add("source", relatePerson.get("name"));
                            linkP.add("target", relatePerson1.get("name"));
                            linkP.add("value", JsonParser.parseString("学生"));
                            if (!allLink.contains(linkP)) {
                                allLink.add(linkP);
                            }
                        }
                    }
                }
                if(relatePerson.has("relation2"))
                {
                    JsonArray relatePersonArray2 = relatePerson.get("relation2").getAsJsonArray();
                    for (int i2 = 0; i2 < relatePersonArray2.size(); i2++) {
                        JsonObject relatePerson2 = relatePersonArray2.get(i2).getAsJsonObject();
                        if (!relatePerson2.get("id").getAsString().equals(personID)
                                && !relatePerson2.get("id").getAsString().equals(relatePerson.get("id").getAsString()))
                        {
                            JsonObject nodeP = new JsonObject();
                            nodeP.add("name", relatePerson2.get("name"));
                            nodeP.add("category", JsonParser.parseString("2"));
                            if (!allNode.contains(nodeP)) {
                                allNode.add(nodeP);
                            }

                            JsonObject linkP = new JsonObject();
                            linkP.add("source", relatePerson.get("name"));
                            linkP.add("target", relatePerson2.get("name"));
                            linkP.add("value", JsonParser.parseString("合作"));
                            if (!allLink.contains(linkP)) {
                                allLink.add(linkP);
                            }
                        }
                    }
                }
            }
        }


        Gson gson = new Gson();
        allInfo.add("nodes", JsonParser.parseString(gson.toJson(allNode)));
        allInfo.add("links", JsonParser.parseString(gson.toJson(allLink)));
        return allInfo;
    }
}