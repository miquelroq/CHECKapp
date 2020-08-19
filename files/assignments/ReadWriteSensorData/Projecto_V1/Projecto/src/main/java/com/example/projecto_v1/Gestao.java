package com.example.projecto_v1;

public class Gestao {

    public void Toma_La_Valores(int nsensor, int valor)
    {
        Uteis.MSG_DEBUG("VALORES", "ID:" + nsensor+ " VALOR:" + valor);
    }
    public void Toma_La_Valores(String X)
    {
        String[] arrOfStr = X.split(",");
        for (String a : arrOfStr)
            System.out.println(a);

        Uteis.MSG_DEBUG("VALORES", X);
    }
    public float MediaValores(int nsensor)
    {
        return -1;
    }
    public float DesvioPadrao(int nsensor)
    {
        return -1;
    }
}
