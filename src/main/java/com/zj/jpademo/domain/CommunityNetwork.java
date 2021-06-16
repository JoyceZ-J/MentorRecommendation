package com.zj.jpademo.domain;

import com.google.gson.*;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import io.grpc.stub.MetadataUtils;

import java.util.*;

public class CommunityNetwork
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
       public List<String> COLOR_LIST = Arrays.asList( "#336699" , "#99CC99"  , "#FF9999"  , "#660099");


       public JsonObject dgraphDatabyid(String cid)
        {
            DgraphClient dgraphClient = createDgraphClient(false);

            // Set schema
            String schema = "name: string @index(exact) .";
            DgraphProto.Operation operation = DgraphProto.Operation.newBuilder().setSchema(schema).build();
            dgraphClient.alter(operation);

            Transaction txn = dgraphClient.newTransaction();
            String query ="";
            //判断输入的是id还是cname
            if(cid.matches("[0-9]{1,}"))
            {
                // Query cid
                query =
                        "query all($a: string) {\n" +
                                "      all(func: eq(cid, $a))\n" +
                                "      {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        }\n" +
                                "        }\n" +
                                "        ~relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        ~relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        }\n" +
                                "        }\n" +
                                "        expand(_all_)\n" +
                                "        {\n" +
                                "         cid\n" +
                                "         cname\n" +
                                "         information\n" +
                                "         expand(_all_)\n" +
                                "        }\n" +
                                "      }\n" +
                                "    }";
            }
            else
            {
                // Query cname
                query =
                        "query all($a: string) {\n" +
                                "      all(func: eq(cname, $a))\n" +
                                "      {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        }\n" +
                                "        }\n" +
                                "        ~relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        ~relation\n" +
                                "        {\n" +
                                "        cid\n" +
                                "        cname\n" +
                                "        information\n" +
                                "        }\n" +
                                "        }\n" +
                                "        expand(_all_)\n" +
                                "        {\n" +
                                "         cid\n" +
                                "         cname\n" +
                                "         information\n" +
                                "         expand(_all_)\n" +
                                "        }\n" +
                                "      }\n" +
                                "    }";
            }
            Map<String, String> vars = Collections.singletonMap("$a", cid);
            DgraphProto.Response res = txn.queryWithVars(query, vars);
            System.out.println(query);

            // Deserialize
            JsonObject ppl = JsonParser.parseString(res.getJson().toStringUtf8()).getAsJsonObject();
            System.out.println(ppl);
            return ppl;
        }


    public Map<String,List<JsonObject>> build_around_graph(String main,JsonArray arounds, List<JsonObject> data,  List<JsonObject> links, String color, String size, String remove)
    {
     /*
     构建中心是main，周围是arounds 的图
    :param main:
    :param around:
    :param data:
    :param links:
    :param remove: 避免重复， 记录什么值不录入
     */
    String source = main;
    for (int i = 0; i < arounds.size(); i++)
    {
        String aroundname = String.valueOf(arounds.get(i).getAsJsonObject().get("cname"));
        String around = String.valueOf(arounds.get(i).getAsJsonObject().get("cid"));
        if(around.equals(source)){continue;}
        JsonObject nodestyle = new JsonObject();
        nodestyle.addProperty("color",color);
        System.out.println(color);
        System.out.println(nodestyle);
        JsonObject node = new JsonObject();
        node.add("name", JsonParser.parseString(aroundname));
        node.add("id", JsonParser.parseString(around));
        //node.addProperty("category", "1");
        node.add("symbolSize",JsonParser.parseString(size));
        node.add("itemStyle", nodestyle);

        if (!data.contains(node)) {
            data.add(node);
        }

        JsonObject link = new JsonObject();
        link.add("source", JsonParser.parseString(source));
        link.add("target", JsonParser.parseString(around));
        if (!links.contains(link)) {
            links.add(link);
        }
      }

        Map<String, List<JsonObject>> arounddata = new HashMap<String, List<JsonObject>>();
        arounddata.put("nodes",data);
        arounddata.put("links",links);
        return arounddata;
    }


    public JsonObject get_data_and_links(String cid)
    {
        JsonObject info = dgraphDatabyid(cid).getAsJsonArray("all").get(0).getAsJsonObject();
        String cname = String.valueOf(info.get("cname"));
        String ccid = String.valueOf(info.get("cid"));
        String cdetail = String.valueOf(info.get("information"));
        JsonObject allInfo = new JsonObject();
        List<JsonObject> allNode = new ArrayList<>();
        List<JsonObject> allLink = new ArrayList<>();

        JsonObject nodeCenter = new JsonObject();
        JsonObject nodestyle = new JsonObject();
        nodestyle.addProperty("color",COLOR_LIST.get(0));

        /*Map<String, String> nodestyle  = new HashMap<String, String>(){{
            put("itemStyle",COLOR_LIST.get(0));
        }};

         */
        nodeCenter.add("name", JsonParser.parseString(cname));
        nodeCenter.add("id", JsonParser.parseString(ccid));
        //nodeCenter.addProperty("category", "0");
        nodeCenter.add("value", JsonParser.parseString(cdetail));
        nodeCenter.add("symbolSize",JsonParser.parseString("90"));
        nodeCenter.add("itemStyle", nodestyle);
        allNode.add(nodeCenter);
        String color1 = COLOR_LIST.get(1);
        String color2 = COLOR_LIST.get(2);
        //relation
        if (info.has("relation"))
        {
            JsonArray relateArray = info.get("relation").getAsJsonArray();

            Map<String,List<JsonObject>> aroundinfo =build_around_graph(ccid, relateArray,allNode, allLink, color1, "50", "None");
            allNode = aroundinfo.get("nodes");
            allLink = aroundinfo.get("links") ;
            for (int i = 0; i < relateArray.size(); i++) {
                JsonObject relate1 = relateArray.get(i).getAsJsonObject();
                //String relate1name = String.valueOf(relate1.get("cname"));
                String relate1id = String.valueOf(relate1.get("cid"));

                if (relate1.has("relation"))
                {
                    JsonArray relateArray2 = relate1.get("relation").getAsJsonArray();
                    Map<String,List<JsonObject>> aroundinfo2 =build_around_graph(relate1id,relateArray2,allNode, allLink, color2, "40", "None");
                    allNode = aroundinfo2.get("nodes");
                    allLink = aroundinfo2.get("links") ;
                }
            }
        }
        //~relation
//        if (info.has("~relation"))
//        {
//            JsonArray relateArray = info.get("~relation").getAsJsonArray();
//            Map<String,List<JsonObject>> aroundinfo =build_around_graph(ccid, relateArray,allNode, allLink, color1, "50", "None");
//            allNode = aroundinfo.get("nodes");
//            allLink = aroundinfo.get("links") ;
//            for (int i = 0; i < relateArray.size(); i++) {
//                JsonObject relate1 = relateArray.get(i).getAsJsonObject();
//                String relate1id = String.valueOf(relate1.get("cid"));
//                if (relate1.has("~relation"))
//                {
//                    JsonArray relateArray2 = relate1.get("~relation").getAsJsonArray();
//                    Map<String,List<JsonObject>> aroundinfo2 =build_around_graph(relate1id, relateArray2,allNode, allLink, color2, "40", "None");
//                    allNode = aroundinfo2.get("nodes");
//                    allLink = aroundinfo2.get("links") ;
//                }
//            }
//        }
        Gson gson = new Gson();
        allInfo.add("nodes", JsonParser.parseString(gson.toJson(allNode)));
        allInfo.add("links", JsonParser.parseString(gson.toJson(allLink)));
        return allInfo;
    }



}
