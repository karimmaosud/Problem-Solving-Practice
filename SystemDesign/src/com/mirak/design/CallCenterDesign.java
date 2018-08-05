package com.mirak.design;

import java.util.List;

public class CallCenterDesign {

  enum Rank {
    RESPONDENT, MANAGER, DIRECTOR
  }

  class CallCenter {
    List<List<Employee>> employeeLevel;

    CallCenter() {

    }

    void receiveCall(String userName) {
      Call call = new Call(userName, Rank.RESPONDENT);
      dispatchCall(call);
    }

    Employee handleCall(Call call) {
      return null;
    }

    void dispatchCall(Call call) {
      Employee assignedEmployee = handleCall(call);
      if(assignedEmployee == null) {
        // no available employee at the moment. make client wait.
      }else {
        assignedEmployee.assignCall(call);
        call.assignEmployee(assignedEmployee);
      }
    }
  }

  class Call {

    Employee employee;
    String userName;
    String summary;
    Rank rank;

    Call(String userName, Rank rank) {
      this.userName = userName;
      this.rank = rank;
    }

    void assignEmployee(Employee employee) {
      this.employee = employee;
    }

    void endCall() {
      this.employee = null;
    }

    void incrementRank() {
      // increments call's rank.
    }
  }

  abstract class Employee {
    Call ongoingCall;
    Rank rank;

    void assignCall(Call call) {
      ongoingCall =  call;
    }

    void endOngoingCall() {
      ongoingCall.summary = "Call ended, issue has been resolved.";
      this.ongoingCall = null;

    }

    void escalateCall() {
      ongoingCall.incrementRank();
    }

    boolean assignNewCall() {
      return isFree();
    }

    boolean isFree() {
      return ongoingCall == null;
    }
  }

  class Respondent extends Employee {

    Respondent() {
      this.rank = Rank.RESPONDENT;
    }
  }

  class Manager extends Employee {

    Manager() {
      this.rank = Rank.MANAGER;
    }
  }

  class Director extends Employee {
    Director() {
      this.rank = Rank.DIRECTOR;
    }
  }
}
