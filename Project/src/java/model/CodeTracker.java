/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class CodeTracker {

    private String id;
    private String name;
    private String configuration;

    public CodeTracker() {
    }

    public CodeTracker(String id) {
        this.id = id;
    }

    public CodeTracker(String id, String name, String configuration) {
        this.id = id;
        this.name = name;
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}
