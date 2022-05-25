package com.example.knowuproject.controle;

import com.example.knowuproject.modelo.*;
import com.example.knowuproject.repositorio.EventoRepository;
import com.example.knowuproject.repositorio.LocalidadeRepository;
import com.example.knowuproject.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/evento")
@CrossOrigin("*")
public class EventoController {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;

    @PostMapping("/criar/{id}")
    public ResponseEntity criarEvento(@PathVariable Integer id, @RequestBody Evento evento) {

        evento.setUsuario(usuarioRepository.findById(id));
        evento.buscarLocalizacao();
        localidadeRepository.save(evento.getLocalidade());
        eventoRepository.save(evento);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/todos")
    public ResponseEntity buscarEvento() {
        return ResponseEntity.status(200).body(eventoRepository.findAll());
    }

    @PatchMapping("/atualizarDadosEvento/{id}")
    public ResponseEntity atualizarEvento(@PathVariable Integer id, @RequestBody Evento evento) {

        Evento dadosEvento = evento;
        Optional<Evento> dados = eventoRepository.findById(id);
        Evento dadosOriginais = dados.get();

        if (evento.getNome() != null) {
            dadosOriginais.setNome(dadosEvento.getNome());
        }
        if (evento.getDescricao() != null) {
            dadosOriginais.setDescricao(dadosEvento.getDescricao());
        }
        if (evento.getDataInicio() != null) {
            dadosOriginais.setDataInicio(dadosEvento.getDataInicio());
        }
        if (evento.getDataFim() != null) {
            dadosOriginais.setDataFim(dadosEvento.getDataFim());
        }

        dadosOriginais.setIdEvento(id);
        eventoRepository.save(dadosOriginais);

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluirEvento(@PathVariable Integer id) {

        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/downloadEvento")
    public ResponseEntity downloadDeEventos() {

        ListaObj<Evento> eventos = new ListaObj<Evento>((int) eventoRepository.count());
        List<Evento> eventosAuxiliar = eventoRepository.findAll();

        for (int i = 0; i < eventoRepository.count(); i++) {
            eventos.adicionar(eventosAuxiliar.get(i));
        }

        gravaArquivoCsv(eventos, "eventos");
        return ResponseEntity.status(200).build();
    }


    @PostMapping("/downloadEvento/{id}")
    public ResponseEntity downloadDeEvento(@PathVariable Integer id) {

        ListaObj<Evento> eventos = new ListaObj<Evento>((int) eventoRepository.count());
        Optional<Evento> eventosAuxiliar = eventoRepository.findById(id);
        eventos.adicionar(eventosAuxiliar.get());

        gravaArquivoCsv(eventos, "eventos");
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/eventos-proximos")
    public ResponseEntity eventosProximos(@RequestBody Localidade localidade) {

        System.out.println(localidade.getLatitute());

        List eventos = localidadeRepository.findByAllEventosProximos(localidade.getLatitute(), localidade.getLongitute());

        if (eventos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(eventos);
    }

    @PostMapping("/atualizareventos-proximos")
    public ResponseEntity AtualizarEventosProximos(@RequestBody Localidade localidade) {

        PilhaObj pilhaObj = new PilhaObj(5);
        for (int i = 0; i < pilhaObj.getTamanho(); i++) {
            pilhaObj.push(localidadeRepository.findByAllEventosProximos(localidade.getLatitute(), localidade.getLongitute()));
        }
        return ResponseEntity.status(200).body(pilhaObj);
    }

    //    Método gravar Arquivo .csv

    public static void gravaArquivoCsv(ListaObj<Evento> listaObj, String nomeArquivo) {

        FileWriter arquivo = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArquivo += ".csv";

        try {
            arquivo = new FileWriter(nomeArquivo, false);
            saida = new Formatter(arquivo);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < listaObj.getTamanho(); i++) {
                Evento event = listaObj.getElemento(i);
                Optional<Usuario> usuario = event.getUsuario();
                saida.format("%d;%s;%s;%s;%s;%s\n",
                        event.getIdEvento(), event.getNome(),
                        event.getDataInicio(), event.getDataFim(),
                        usuario.get().getNome(), event.getDescricao());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {
            saida.close();

            try {
                arquivo.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
        }
        if (deuRuim) {
            System.exit(1);
        }
    }

    public static void lerExibeArquivoCsv(String nomeArquivo) {
        FileReader arquivo = null;
        Scanner entrada = null;
        Boolean deuRuim = false;
        nomeArquivo += ".csv";

        try {
            arquivo = new FileReader(nomeArquivo);
            entrada = new Scanner(arquivo).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encotrado");
            System.exit(1);
        }

        try {
            System.out.printf("%4s %-15s %-9s %-9s %-15s %s\n", "ID", "NOME", "DATA INÍCIO", "DATA FIM", "ORGANIZADOR",
                    "DESCRIÇÃO");
            while (entrada.hasNext()) {
                Integer id = entrada.nextInt();
                String nome = entrada.next();
                String dataInicio = entrada.next();
                String dataFim = entrada.next();
                String organizador = entrada.next();
                String descricao = entrada.next();
                System.out.printf("%4d %-15s %-9s %-9s %-15s %s\n", id, nome, dataInicio, dataFim, organizador, descricao);
            }
        } catch (NoSuchElementException erro) {
            System.out.printf("Arquivo com problemas!");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arquivo.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
//    Fim método
}
