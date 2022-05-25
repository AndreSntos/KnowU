package com.example.knowuproject.controle;

import com.example.knowuproject.dto.Usuariodto;
import com.example.knowuproject.modelo.FilaObj;
import com.example.knowuproject.modelo.Localidade;
import com.example.knowuproject.modelo.Usuario;
import com.example.knowuproject.repositorio.LocalidadeRepository;
import com.example.knowuproject.repositorio.UsuarioRepository;
import com.example.knowuproject.requisicao.UsuarioAtualizarDadosRequest;
import com.example.knowuproject.requisicao.UsuarioAvaliacaoResponse;
import com.example.knowuproject.requisicao.UsuarioRecuperarSenhaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.validation.Valid;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    List<Usuariodto> usuariodtos;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;

    public UsuarioController() {
        this.usuariodtos = new ArrayList<>();
    }

    @PatchMapping("/foto/{idUsuario}")
    public ResponseEntity patchFoto(@PathVariable int idUsuario, @RequestParam MultipartFile foto) throws IOException { //MultipartFile indica o tipo de arquivo
        if (usuarioRepository.existsById(idUsuario)) {
            Usuario user = usuarioRepository.findById(idUsuario).get();
            byte[] novaFoto = foto.getBytes();
            user.setFoto(novaFoto);
            usuarioRepository.save(user);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity getFoto(@PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            Optional<Usuario> user = usuarioRepository.findById(id);
            // return ResponseEntity.status(200).header("content-type", "image/jpeg")
            //        .body(user.getFoto());
            return ResponseEntity.status(200).body(user.get().getFoto());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/adicionar")
    public ResponseEntity adicionarUsuario(@RequestBody @Valid Usuario usuario) {
        usuarioRepository.save(usuario);
        listaUsuarioDocLayout(usuario);
        leArquivoUsuarioTxt("usuarios.txt");
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario) {

        Optional<Usuario> usuarioLogin = Optional.ofNullable(usuarioRepository.findByUsuario(usuario.getUsuario()));

        if (usuario.getUsuario().equals(usuarioLogin.get().getUsuario()) && usuario.senha().equals(usuarioLogin.get().senha())) {
            usuario = usuarioLogin.get();
            usuario.login();
            usuario.setIdUsuario(usuarioLogin.get().getIdUsuario());
            localidadeRepository.save(usuario.getLocalidade());
            usuarioRepository.save(usuario);
            return ResponseEntity.status(200).body(usuario.getIdUsuario());
        }

        return ResponseEntity.status(204).build();
    }

//    @DeleteMapping("/excluir")
//    public String removerUsuario(@RequestBody Usuariodto usuariodto) {
//
//        String frase = "";
//
//        for (int i = 0; i < usuariodtos.size(); i++) {
//
//            if (usuariodtos.get(i).getUsuario().equals(usuariodto.getUsuario())) {
//                frase = String.format("Removendo o usuário %s", usuariodto.getUsuario());
//                usuariodtos.remove(usuariodtos.get(i));
//            }
//        }
//        return frase;
//    }

    @GetMapping("/todos")
    public ResponseEntity exibirUsuarios() {
        return ResponseEntity.status(200).body(usuarioRepository.findAll());
    }

    @GetMapping("/nome-usuario/{id}")
    public ResponseEntity exibirNomeUsuario(@PathVariable Integer id) {
        System.out.println(usuarioRepository.listarNomeAndUsuario(id));
        return ResponseEntity.of(Optional.of(usuarioRepository.listarNomeAndUsuario(id)));
    }

//    @GetMapping("/dados-usuario/{id}")
//    public ResponseEntity exibirDadosUsuario(@PathVariable Integer id) {
//
//    }

    @PutMapping("/logoff/{id}")
    public ResponseEntity deslogarUsuario(@PathVariable Integer id) {

        Optional<Usuario> usuarios = usuarioRepository.findById(id);
        if (!usuarios.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario = usuarios.get();
            usuario.logoff();
            usuarioRepository.save(usuario);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/avaliar-usuario/{id}")
    public ResponseEntity avaliarUsuario(@RequestBody UsuarioAvaliacaoResponse usuario,
                                         @PathVariable Integer id) {

        Optional<Usuario> usuarioAux = Optional.of(new Usuario());
        UsuarioAvaliacaoResponse usuario1 = new UsuarioAvaliacaoResponse();
        usuarioAux = usuarioRepository.findById(id);

        if (usuarioRepository.existsById(id)) {
            if (usuario.getAvaliacao() > 0) {
                Integer avaliacao = usuarioAux.get().getAvaliacao() + 2;
                if (avaliacao < 100) {
                    usuario1.setAvaliacao(avaliacao);
                    usuario1.setIdUsuario(id);
                    usuarioRepository.atualizarAvaliacao(usuario1.getIdUsuario(), usuario1.getAvaliacao());
                }
            } else {
                Integer avaliacao = usuarioAux.get().getAvaliacao() - 3;
                if (avaliacao > 0) {
                    usuario1.setAvaliacao(avaliacao);
                    usuario1.setIdUsuario(id);
                    usuarioRepository.atualizarAvaliacao(usuario1.getIdUsuario(), usuario1.getAvaliacao());
                }
            }
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(204).build();


    }

    @PostMapping("/atualizarusuarios-proximos")
    public ResponseEntity usuariosProximos(@RequestBody Localidade localidade) {


        FilaObj filaObj = new FilaObj(3);
        do {
            filaObj.insert(localidadeRepository.findByAllUsuariosProximos(localidade.getLatitute(), localidade.getLongitute()));
        } while (!filaObj.isFull());
        System.out.println(filaObj.peek());
        return ResponseEntity.status(200).body(filaObj.peek());
    }

    @PatchMapping("/enviarCodigo")
    public ResponseEntity enviarCodigoRecuperacaoSenha(@RequestBody Usuario usuario) {

        Optional<Usuario> usuarios = Optional.ofNullable(usuarioRepository.findByEmail(usuario.getEmail()));
        System.out.println(usuarios.get());
        if (!usuarios.isEmpty()) {

            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("knowu@outlook.com.br", "#Gfgrupo5");
                        }
                    });

            /** Ativa Debug para sessão */
            session.setDebug(true);


            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("knowu@outlook.com.br")); //Remetente

                UsuarioRecuperarSenhaRequest usuario1 = new UsuarioRecuperarSenhaRequest();
                usuario1.setCodigoRecuperaSenha(ThreadLocalRandom.current().nextInt(10000, 99999));
                usuario1.setIdUsuario(usuarios.get().getIdUsuario());
                usuario1.setNome(usuarios.get().getNome());
                usuarioRepository.atualizarCodigoDeRecuperaçãoSenha(usuario1.getIdUsuario(), usuario1.getCodigoRecuperaSenha());

                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(usuario.getEmail())); //Destinatário(s)
                message.setSubject("Código de recuperação de senha"); //Assunto
                message.setText(String.format("Prezado(a) %s, espero que esteja tendo um ótimo dia! ☺\n\n" +
                                "Para recuperar sua senha, digite o código abaixo no site\n" +
                                "%d\n\n" +
                                "KnowU, revolucionando o jeito de fazer novas amizades",
                        usuario1.getNome(), usuario1.getCodigoRecuperaSenha()));

                /**Método para enviar a mensagem criada*/
                Transport.send(message);

                return ResponseEntity.status(200).build();

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }


        return ResponseEntity.status(204).build();

    }

    @PostMapping("/validarCodigo")
    public ResponseEntity validarCodigoRecuperacao(@RequestBody Usuario usuario) {

        Optional<Usuario> usuario1 = Optional.ofNullable((usuarioRepository.findByCodigoRecuperaSenha(usuario.getCodigoRecuperaSenha())));

        if (!usuario1.isEmpty()) {
            return ResponseEntity.status(200).body(usuario1.get().getIdUsuario());

        }

        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/atualizarDadosConta/{id}")
    public ResponseEntity atualizarDadosConta(@PathVariable Integer id,
                                              @RequestBody Usuario usuario) {

        Usuario dadosUsuario = usuario;
        Optional<Usuario> dados = usuarioRepository.findById(id);
        UsuarioAtualizarDadosRequest dadosOriginais = new UsuarioAtualizarDadosRequest(dados.get().getIdUsuario(), dados.get().getDataNascimento(), dados.get().getGenero(),
                dados.get().getEmail(), dados.get().getSenha(), dados.get().getNome(), dados.get().getUsuario(), dados.get().getDescricao());

//        Dados da conta
        if (usuario.getDataNascimento() != null) {
            dadosOriginais.setDataNascimento(dadosUsuario.getDataNascimento());
        }

        if (usuario.getGenero() != null) {
            dadosOriginais.setGenero(dadosUsuario.getGenero());
        }

        if (usuario.getEmail() != null) {
            dadosOriginais.setEmail(dadosUsuario.getEmail());
        }

        if (usuario.senha() != null) {
            dadosOriginais.setSenha(dadosUsuario.senha());
        }
//        Fim dados da conta

//        Dados do perfil
        if (usuario.getNome() != null) {
            dadosOriginais.setNome(dadosUsuario.getNome());
        }

        if (usuario.getUsuario() != null) {
            dadosOriginais.setUsuario(dadosUsuario.getUsuario());
        }

        if (usuario.getDescricao() != null) {
            dadosOriginais.setDescricao(dadosUsuario.getDescricao());
        }
//        Fim dados do perfil

        dadosOriginais.setIdUsuario(id);
        usuarioRepository.atualizarDadosDaConta(dadosOriginais.getIdUsuario(), dadosOriginais.getDataNascimento(), dadosOriginais.getGenero(), dadosOriginais.getEmail(), dadosOriginais.getSenha(), dadosOriginais.getNome(), dadosOriginais.getUsuario(), dadosOriginais.getDescricao());

        return ResponseEntity.status(200).build();
    }

    public static void listaUsuarioDocLayout(Usuario usuario) {
        usuario.setDescricao(null);
        usuario.setSenha(null);
        usuario.setAutenticado(null);
        usuario.setCodigoRecuperaSenha(null);
        usuario.setAutenticado(null);
        usuario.setAutenticadoEm(null);
        usuario.setLocalidade(null);
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(usuario);
        gravaArquivoTxt(listaUsuarios, "usuarios.txt");
        //leArquivoUsuarioTxt("usuarios.txt");
    }

    public static void gravaRegistroUsuario(String registro, String nomeArq) {
        BufferedWriter saida = null;
        //Abre Arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro.getMessage());
        }
        //Grava o registro e fecha o arquivo, mas ainda não gravou o arquivo!
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo: " + erro.getMessage());
        }
    }

    public static void gravaArquivoTxt(List<Usuario> lista, String nomeArq) {
        int contaRegDados = 0; //São os registros de corpo do documento de layout

        //Monta o registro de Header
        String header = "US00USUARIO"; //Aqui é onde cria o documento de layout
        Date dataDeHoje = new Date();        //Formato no padrão Java, não é o desejado, então criar objeto para formatar...
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); //Data e horário formatado
        header += formataData.format(dataDeHoje);
        header += "v01"; //‘String’ para mostrar a versão do documento

        //Grava o registro de header
        gravaRegistroUsuario(header, nomeArq);

        //Monta e grava o corpo do arquivo
        for (Usuario user : lista) {
            String corpo = "US01"; //Recebe o registro de dados (seguindo o documento de layout)
            corpo += String.format("%4d", user.getIdUsuario());
            corpo += String.format("%-50s", user.getNome());
            corpo += String.format("%-10s", user.getUsuario()); //VERIFICAR SEQUÊNCIA DOS ATRIBUTOS DA CLASSE USUÁRIO!
            corpo += String.format("%-14s", user.getCelular());
            corpo += String.format("%-40s", user.getEmail());
            //corpo += String.format("%-40.40s", user.getDescricao());
            corpo += String.format("%-14s", user.getCpf());
            corpo += String.format("%-10s", user.getDataNascimento());
            corpo += String.format("%-15s", user.getGenero());
            //corpo += String.format("%05d", user.getCodigoRecuperaSenha());
            //corpo += String.format("%b", user.getAutenticado());
            //corpo += String.format("%-40.40s", user.getAutenticadoEm());

            contaRegDados++;
            gravaRegistroUsuario(corpo, nomeArq);
        }

        //Monta e grava o registro Usuário trailer
        String trailer = "US02";                            //Lá do documento de layout
        trailer += String.format(": %d dados registrados", contaRegDados);
        gravaRegistroUsuario(trailer, nomeArq);
    }

    public static void leArquivoUsuarioTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;

        //Atributos para
        int idUsuario;
        String nome;
        String usuario;  //VERIFICAR SEQUÊNCIA DOS ATRIBUTOS DA CLASSE USUÁRIO!
        String celular;
        String email;
        String cpf;
        String dataNascimento;
        String genero;
        //String localizacao;
        int qtdRegistrosGravados;
        int contaRegDados = 0;

        List<Usuario> listaLidaDeUsuarios = new ArrayList<>();

        //Abre o arquivo
        try {
            entrada = new BufferedReader(new BufferedReader(new FileReader(nomeArq)));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro.getMessage());
        }

        //Lê o arquivo
        try {
            //lê o primeiro registro do arquivo
            registro = entrada.readLine();
            while (registro != null) {                    //Enquanto diferir de NULL é porque não chegou ao fim do arquivo
                tipoRegistro = registro.substring(0, 4); //lê o 0, 1, 2 e 3, mas vai até o 4, então no próximo começa do 4 até o seu final
                if (tipoRegistro.equals("US00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(0, 11));
                    System.out.println("Data/hora de gravação do arquivo: " + registro.substring(11, 30));
                    System.out.println("Versão do layout: " + registro.substring(30, 33));

                } else if (tipoRegistro.equals("US02")) {
                    System.out.println("Trailer");
                    qtdRegistrosGravados = Integer.parseInt(registro.substring(5, 8).trim());
                    if (qtdRegistrosGravados == listaLidaDeUsuarios.size()) {
                        System.out.println("Quantidade de registros gravados compatível com a quantidade lida");
                    } else {
                        System.out.println("Quantidade de registros gravados não confere com a quantidade lida");
                    }

                } else if (tipoRegistro.equals("US01")) {
                    System.out.println("Corpo");
                    idUsuario = Integer.parseInt(registro.substring(4, 8).trim());
                    nome = registro.substring(8, 50);
                    usuario = registro.substring(59, 70);                    //VERIFICAR SEQUÊNCIA DOS ATRIBUTOS DA CLASSE USUÁRIO!
                    celular = registro.substring(70, 82); //trim();
                    email = registro.substring(82, 122); //trim();
                    cpf = registro.substring(122, 136);
                    dataNascimento = registro.substring(136, 146);
                    genero = registro.substring(146, 147).trim();
                    //localizacao = registro.substring(105,110);
                    Usuario user = new Usuario(idUsuario, nome, usuario, celular, email, cpf, dataNascimento, genero);
                    listaLidaDeUsuarios.add(user);
                    //contaRegDados++;
                } else {
                    System.out.println("Tipo de registro inválido!");
                }
                //Lê o próximo registro
                registro = entrada.readLine();
            }

            entrada.close();

        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro.getMessage());
        }

        System.out.println("\nConteúdo lido do arquivo");
        for (Usuario user : listaLidaDeUsuarios) {
            System.out.println(user);
        }
    }
}

