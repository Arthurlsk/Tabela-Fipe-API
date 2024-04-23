package br.com.alura.fipe.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCarro(@JsonAlias("codigo") String codigo,
                         @JsonAlias("nome") String nome) {



}



