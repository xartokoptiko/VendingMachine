package com.karachristos.vending.vendingmachine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karachristos.vending.vendingmachine.entities.Machine;
import com.karachristos.vending.vendingmachine.entities.Merchant;
import com.karachristos.vending.vendingmachine.utils.MachineAdapter;
import com.karachristos.vending.vendingmachine.utils.MerchantAdapter;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Data {
    public List<Merchant> getMerchants() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Merchant.class, new MerchantAdapter());
        Gson gson = gsonBuilder.create();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(VendingMachineApplication.class.getResourceAsStream("Data/Products.json")), StandardCharsets.UTF_8)) {
            Merchant[] merchants = gson.fromJson(reader, Merchant[].class);
            List<Merchant> merchantList = new ArrayList<>();
            Collections.addAll(merchantList, merchants);
            return (merchantList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Machine getMachine() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Machine.class, new MachineAdapter());
        Gson gson = gsonBuilder.create();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(VendingMachineApplication.class.getResourceAsStream("Data/Machine.json")), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Machine.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeMachine(Machine machine){
        GsonBuilder gsonBuilder = new GsonBuilder();
        MachineAdapter machineAdapter = new MachineAdapter();
        gsonBuilder.registerTypeAdapter(Machine.class, machineAdapter);
        Gson gson = gsonBuilder.create();
        String filePath = "path/to/machine.json";
        try {
            URL resourceUrl = VendingMachineApplication.class.getResource("Data/Machine.json");
            File file = new File(Objects.requireNonNull(resourceUrl).getPath());
            FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
            gson.toJson(machine, writer);
            writer.close();
            System.out.println("[INFO] Machine created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

