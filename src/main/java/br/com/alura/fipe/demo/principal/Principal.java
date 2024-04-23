package br.com.alura.fipe.demo.principal;

import br.com.alura.fipe.demo.model.DadosCarro;
import br.com.alura.fipe.demo.model.Modelos;
import br.com.alura.fipe.demo.model.Veiculo;
import br.com.alura.fipe.demo.service.ConsumoApi;
import br.com.alura.fipe.demo.service.ConverteDados;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private ConverteDados conversor = new ConverteDados();

    private ConsumoApi consumo = new ConsumoApi();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    Scanner scanner = new Scanner(System.in);

    public void exibeMenu() {

        String menu = """
                **** OPÇÕES ****
                            
                Carros
                            
                Motos
                            
                Caminhoes
                            
                Digite uma das opções para consultar valores:
                """;

        System.out.println(menu);
        String opcao = scanner.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }


        var json = consumo.obterDados(endereco);
        //System.out.println(json);
        //convertendo esse json array na classe dados carro
        var marcas = conversor.obterLista(json, DadosCarro.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosCarro::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta");
        var codigoMarca = scanner.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);
        System.out.println("\n Modelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosCarro::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var nomeCarro = scanner.nextLine();

        List <DadosCarro> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeCarro.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o código do modelo filtrado ");
        var codigoModelo = scanner.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumo.obterDados(endereco);
        List<DadosCarro> anos = conversor.obterLista(json, DadosCarro.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for(int i = 0; i<anos.size(); i++){
            var enderecoAnos = endereco +  "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);
    }
}
