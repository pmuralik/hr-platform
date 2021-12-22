package com.workmotion.projects.hrplatform.model;

import java.util.*;


@SuppressWarnings("ALL")
public enum EmployeeState {

    ADDED{
        @Override
        public List<EmployeeState> nextState() {
            return new ArrayList<EmployeeState>(Arrays.asList(IN_CHECK));
        }
    },
    IN_CHECK{
        @Override
        public List<EmployeeState> nextState() {
            return new ArrayList<EmployeeState>(Arrays.asList(APPROVED));
        }
    },
    APPROVED{
        @Override
        public List<EmployeeState> nextState() {
            return new ArrayList<EmployeeState>(Arrays.asList(IN_CHECK,ACTIVE));
        }
    },
    ACTIVE{
        @Override
        public List<EmployeeState> nextState() {
            return new ArrayList<EmployeeState>(Arrays.asList(this));
        }
    };

    public abstract List<EmployeeState> nextState();
}
