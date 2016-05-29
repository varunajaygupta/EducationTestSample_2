package com.example.novo.educationtestsample.Utils;

/**
 * Created by anubhav on 26/5/16.
 */
public class JacksonHandler {
    public JacksonHandler() {
    }

   /* public static String serialize(BaseModel model) {
        JSONSerializer serializer = (new JSONSerializer()).exclude(new String[]{"*.class"}).exclude(new String[]{"*.stackTrace"}).exclude(new String[]{"*.ourStackTrace"}).exclude(new String[]{"*.internalStackTrace"}).transform(new DateTransformer("MM/dd/yyyy"), new Class[]{Date.class}).transform(new DateTransformer("yyyy-MM-dd\'T\'HH:mm:ss"), new Class[]{Timestamp.class});
        StringWriter writer = new StringWriter();
        BaseModel container = new BaseModel();
        serializer.deepSerialize(container, writer);
        return writer.toString();
    }

    public static BaseModel deserialize(String json) {
        BaseModel serviceModelContainer = (BaseModel) (new JSONDeserializer()).use(Timestamp.class, new SqlTimestampTransformer("yyyy-MM-dd\'T\'HH:mm:ss")).use(Date.class, new DateTransformer("MM/dd/yyyy")).use((String)null, ServiceModelContainer.class).deserialize(json);
        return serviceModelContainer;
    }

    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("C:\\Users\\db2admin\\json-in.json");
        BaseModel model = deserialize(fileToString(fileReader));
        System.out.println(model);
        System.out.println("***********************");
        System.out.println(serialize(model));
    }

    private static String fileToString(Reader reader) throws Exception {
        String fileStr = "";
        BufferedReader buffReader = new BufferedReader(reader);

        for(String lineStr = buffReader.readLine(); lineStr != null; lineStr = buffReader.readLine()) {
            fileStr = fileStr + lineStr;
        }

        return fileStr;
    }*/
}
