package ro.var.noteapplication.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.var.noteapplication.models.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOServiceImpl implements IOService {
    @Override
    public List<Note> readFile(File file) {
        JSONParser jsonParser = new JSONParser();
        List<Note> noteList = new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            for (Object object : jsonArray) {
                JSONObject noteObject = (JSONObject) object;
                String title = (String) noteObject.get("title");
                String body = (String) noteObject.get("body");
                Long creationDate = (Long) noteObject.get("creationDate");
                Long modificationDate = (Long) noteObject.get("modificationDate");
                Boolean markAsFinished = (Boolean) noteObject.get("markAsFinished");
                List hashtagList = (List) noteObject.get("hashtagList");
                Note note = new Note(title, body, creationDate, modificationDate, markAsFinished, hashtagList);
                noteList.add(note);
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    @Override
    public void writeFile(List<Note> noteList, File file) {
        Runnable runnable = () -> {
            JSONArray jsonArray = new JSONArray();
            try (FileWriter writer = new FileWriter(file)) {
                for (Note note : noteList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("title", note.getTitle());
                    jsonObject.put("body", note.getBody());
                    jsonObject.put("creationDate", note.getCreationDate());
                    jsonObject.put("modificationDate", note.getModificationDate());
                    jsonObject.put("markAsFinished", note.isMarkAsFinished());
                    jsonObject.put("hashtagList", note.getHashtagList());
                    jsonArray.add(jsonObject);
                }

                writer.write(jsonArray.toJSONString());
                writer.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        Thread writeThread = new Thread(runnable);
        writeThread.start();
    }
}
