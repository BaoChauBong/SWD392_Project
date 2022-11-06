/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.CodeTracker;
import model.IssueTracker;
import model.TestProject;

/**
 *
 * @author Admin
 */
public interface ITestProjectDAO {

    public ArrayList<TestProject> getAllTestProject();

    public boolean editTestProject(TestProject a);

    public boolean deleteTestProject(String name);

    public boolean insertTestProject1(TestProject a);

    public TestProject getTestProjectByName(String name);

    public ArrayList<IssueTracker> listIssueTracker();

    public ArrayList<CodeTracker> listCodeTracker();

}
