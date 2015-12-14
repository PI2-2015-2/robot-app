package com.br.robot_app.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * Blocks hold all the instruction in JSON objects as strings
 */
public class Block{

    public int blockId;
    public String function;
    public List<String> params;
    public List<String> values;
    public JSONObject instructions;

    public Block(){
        this.instructions = new JSONObject();
    }

    /**
     * Adding a new instruction on the block
     */
    public void generateInstruction() {
        this.instructions = new JSONObject();
        int numParam = params.size();
        LinkedHashMap hashParam = new LinkedHashMap();
        for (int i = 0; i < numParam; i++)
            hashParam.put(params.get(i), values.get(i));
        try {
            instructions.put(function, hashParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO: make a map of param and function
     * @param function
     * @param params
     * @param values
     */
    public void setInstruction(String function, List<String> params, List<String> values){
        this.function = function;
        this.params = params;
        this.values = values;
    }
}