package codigo;

import java.util.*;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serie {
    public String idSerie;
    private String Nome;
    private String DataDeLancamento;

    public Serie(String idSerie, String Nome, String DataDeLancamento) {
        this.idSerie = idSerie;
        this.Nome = Nome;
        this.DataDeLancamento = DataDeLancamento;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void printaSerie(){
        System.out.println("Id da Série: " + this.idSerie + "\nNome da Série: " + this.Nome + "\nData de Lançamento: " + this.DataDeLancamento);
    }
}