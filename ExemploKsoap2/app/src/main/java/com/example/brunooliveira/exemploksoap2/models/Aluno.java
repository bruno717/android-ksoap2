package com.example.brunooliveira.exemploksoap2.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by bruno.oliveira on 29/09/2015.
 */
public class Aluno implements KvmSerializable {

    private Integer id;
    private String name;
    private String curso;

    @Override
    public Object getProperty(int index) {

        switch (index) {
            case 0:
                return id;
            case 1:
                return name;
            case 2:
                return curso;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                this.id = Integer.parseInt(value.toString());
                break;
            case 1:
                this.name = value.toString();
                break;
            case 2:
                this.curso = value.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

        switch (index) {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "name";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "curso";
                break;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
