package com.zj.jpademo.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
                        "        uid\n" +
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
}