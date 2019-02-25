package me.wattguy.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.IOException;
import java.io.StringWriter;

import me.wattguy.snake.enums.Control;

public class Config {

    public static Control CONTROL = Control.SWIPES;
    public static Integer RECORD = 0;
    public static Boolean SOUNDS = true;
    public static Integer COINS = 0;

    public static void initialize(){

        FileHandle file = Gdx.files.local("settings.json");
        if (file != null){

            try {
                if (file.exists()) {
                    JsonValue jv = new JsonReader().parse(file.readString());

                    CONTROL = Control.valueOf(jv.getString("control"));
                    RECORD = jv.getInt("record");
                    SOUNDS = jv.getBoolean("sounds");
                    COINS = jv.getInt("coins");

                    return;

                }
            }catch(Exception ignored){ }

            save();

        }

    }

    public static void save(){

        FileHandle file = Gdx.files.local("settings.json");
        StringWriter writer = new StringWriter();
        Json json = new Json(JsonWriter.OutputType.json);
        json.setWriter(writer);

        json.writeObjectStart();
        json.writeValue("control", CONTROL.toString(), String.class);
        json.writeValue("record", RECORD, Integer.class);
        json.writeValue("sounds", SOUNDS, Boolean.class);
        json.writeValue("coins", COINS, Integer.class);
        json.writeObjectEnd();

        file.writeString(writer.toString(), false);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setControl(Control c){

        CONTROL = c;
        save();

    }

    public static void setRecord(Integer i){

        RECORD = i;
        save();

    }

    public static void setSounds(Boolean b){

        SOUNDS = b;
        save();

    }

    public static void setCoins(Integer i){

        COINS = i;
        save();

    }

}
