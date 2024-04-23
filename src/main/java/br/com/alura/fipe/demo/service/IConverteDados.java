package br.com.alura.fipe.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IConverteDados {

    //Chamando algum dado qualquer no caso <T> T = Genérico
    //Recebe um json e uma classe nos parametros
    //Na classe Converte dados, esse json vai ser transformado na classe que foi indicada

    <T> T obterDados(String json, Class<T> classe);

    <T> List <T> obterLista(String json, Class<T> classe);


}
