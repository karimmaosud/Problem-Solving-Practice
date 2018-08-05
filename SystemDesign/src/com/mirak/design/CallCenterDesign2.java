package com.mirak.design;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenterDesign2 {

  enum Rank {
    RESPONDENT, MANAGER, DIRECTOR
  }

  class CallCenter {

    final int LEVEL_NUM = 10;
    Map<Rank, LinkedList<Employee>> employees;
    ConcurrentLinkedQueue<Call> pendingCalls;

    CallCenter() {
      employees = new HashMap<>();
      for (Rank rank : Rank.values()) {
        employees.put(rank, getInitialEmployees(rank));
      }
      pendingCalls = new ConcurrentLinkedQueue<>();
    }


    void receiveCall(String userName) {
      Call call = new Call(userName, Rank.RESPONDENT);
      this.pendingCalls.add(call);
    }

    Employee getAssignedEmployee(Call call) {
      if (this.employees.get(call.getRank()).isEmpty()) {
        return null;
      }
      return employees.get(call.getRank()).removeFirst();
    }

    // Scheduled evey X seconds.
    void dispatchCall() {
      if (!pendingCalls.isEmpty()) {
        Call pendingCall = pendingCalls.poll();
        Employee assignedEmployee = getAssignedEmployee(pendingCall);
        if (assignedEmployee == null) {
          pendingCalls.add(pendingCall);
        } else {
          assignedEmployee.receiveCall(pendingCall);
          pendingCall.assignEmployee(assignedEmployee);
        }
      }
    }

    LinkedList<Employee> getInitialEmployees(Rank rank) {
      LinkedList<Employee> employeesList = new LinkedList<>();
      for (int i = 0; i < LEVEL_NUM; i++) {
        employeesList.addLast(getNewEmployeeFromRank(rank));
      }
      return employeesList;
    }

    private Employee getNewEmployeeFromRank(Rank rank) {
      if (rank == Rank.RESPONDENT) {
        return new Respondent(this);
      } else if (rank == Rank.MANAGER) {
        return new Manager(this);
      }
      return new Director(this);
    }

    public void escalate(Call ongoingCall) {
      Rank nextRank = getNextRank(ongoingCall.getRank());
      if (nextRank == null) {
        // call must be resolved, e2t3 el 5at w 5alas.
        return;
      }
      ongoingCall.setRank(nextRank);
      pendingCalls.add(ongoingCall);
    }

    private Rank getNextRank(Rank rank) {
      if (rank == Rank.RESPONDENT) {
        return Rank.DIRECTOR;
      }
      if (rank == Rank.MANAGER) {
        return Rank.DIRECTOR;
      }
      return null;
    }
  }

  class Call {

    Employee employee;
    String userName;
    Rank rank;

    Call(String userName, Rank rank) {
      this.userName = userName;
      this.rank = rank;
    }

    void setRank(Rank rank) {
      this.rank = rank;
    }

    Rank getRank() {
      return this.rank;
    }

    void assignEmployee(Employee employee) {
      this.employee = employee;
    }
  }

  abstract class Employee {

    Call ongoingCall;
    CallCenter callCenter;
    Rank rank;

    Employee(CallCenter callCenter) {
      this.callCenter = callCenter;
    }

    void receiveCall(Call call) {
      this.ongoingCall = call;
    }

    void endOngingCall() {
      this.ongoingCall = null;
    }

    void escalateCall() {
      this.callCenter.escalate(ongoingCall);
      this.ongoingCall = null;
    }

    boolean assignNewCall() {
      return isFree();
    }

    boolean isFree() {
      return ongoingCall == null;
    }
  }

  class Respondent extends Employee {

    Respondent(CallCenter callCenter) {
      super(callCenter);
      this.rank = Rank.RESPONDENT;
    }
  }

  class Manager extends Employee {

    Manager(CallCenter callCenter) {
      super(callCenter);
      this.rank = Rank.MANAGER;
    }
  }

  class Director extends Employee {

    Director(CallCenter callCenter) {
      super(callCenter);
      this.rank = Rank.DIRECTOR;
    }
  }

}
