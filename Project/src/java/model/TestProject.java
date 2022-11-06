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
public class TestProject {
    private String name;
    private String prefix;
    private String description;
    private boolean isActive;
    private boolean isPublic;
    private IssueTracker issueTracker;
    private CodeTracker codeTracker;

    public TestProject() {
    }

    public TestProject(String name, String prefix, String description, boolean isActive, boolean isPublic, IssueTracker issueTracker, CodeTracker codeTracker) {
        this.name = name;
        this.prefix = prefix;
        this.description = description;
        this.isActive = isActive;
        this.isPublic = isPublic;
        this.issueTracker = issueTracker;
        this.codeTracker = codeTracker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public IssueTracker getIssueTracker() {
        return issueTracker;
    }

    public void setIssueTracker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
    }

    public CodeTracker getCodeTracker() {
        return codeTracker;
    }

    public void setCodeTracker(CodeTracker codeTracker) {
        this.codeTracker = codeTracker;
    }
    
    
}

