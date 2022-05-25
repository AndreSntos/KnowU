package com.example.knowuproject.requisicao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioCadastroSimplesTest {
    @Test
    @DisplayName("O construtor de cadastro deve estar preenchido")
        // do pacote org.junit.jupiter.api...
    void construtorUsuarioCadastro_devePreencherCampos() {

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Dylan Colonhesi", cadastroTest.getNome());
        assertEquals("dylancolonhesi", cadastroTest.getUsuario());
        assertEquals("colonhesidylan@gmail.com", cadastroTest.getEmail());
        assertEquals("48720863845", cadastroTest.getCpf());
        assertEquals("13-02-2002", cadastroTest.getDataNascimento());
        assertEquals("Masculino", cadastroTest.getGenero());
        assertEquals("Abcd@1234", cadastroTest.getSenha());
        assertEquals("Abcd@1234", cadastroTest.getConfirmarSenha());
    }


    @Test
    @DisplayName("O campo nome deve estar preenchido")
    void campoNomeDeveSerPreenchidoNoConstrutorSemCaracteresEspeciais(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Dylan Colonhesi", cadastroTest.getNome());

    }

    @Test
    @DisplayName("O campo nome não deve ter caracteres especiais")
    void campoNomeNãoDeveTerCaracteresEspeciais(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Dylan Colonhesi", cadastroTest.getNome());

    }

    @Test
    @DisplayName("Não deve haver números no campo nome")
    void campoNomeNãoDeveTerNumeros(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Dylan Colonhesi", cadastroTest.getNome());

    }

    @Test
    @DisplayName("O campo nome não deve ter a primeira letra minuscula")
    void campoNomeNãoDeveTerAPrimeiraLetraMinuscula(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Dylan Colonhesi", cadastroTest.getNome());

    }

    @Test
    @DisplayName("O campo usuario deve estar preenchido")
    void campoUsuarioDeveSerPreenchidoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("dylancolonhesi", cadastroTest.getUsuario());

    }

    @Test
    @DisplayName("O campo usuario deve estar preenchido sem espaços em branco")
    void campoUsuarioDeveSerPreenchidoSemEspaçosEmBrancoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("dylancolonhesi", cadastroTest.getUsuario());

    }

    @Test
    @DisplayName("O campo email deve estar preenchido")
    void campoEmailDeveSerPreenchidoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("colonhesidylan@gmail.com", cadastroTest.getEmail());

    }

    @Test
    @DisplayName("Deve verificar se exist o '@' no email")
    void deveVerificarSeExisteOArroba(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("colonhesidylan@gmail.com", cadastroTest.getEmail());

    }

    @Test
    @DisplayName("Deve verificar se exist o '.com' no email")
    void deveVerificarSeExistePontoCom(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("colonhesidylan@gmail.com", cadastroTest.getEmail());

    }

    @Test
    @DisplayName("O campo CPF deve estar preenchido")
    void campoCPFDeveSerPreenchidoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("48720863845", cadastroTest.getCpf());

    }

    @Test
    @DisplayName("O campo CPF deve estar preenchido com 14 caracteres")
    void campoCPFDeveSerPreenchidoCom14Caracteres(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("48720863845", cadastroTest.getCpf());

    }

    @Test
    @DisplayName("O campo data de nascimento deve estar preenchido")
    void campoDataDeNascimentoDeveSerPreenchidoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("13-02-2002", cadastroTest.getDataNascimento());

    }

    @Test
    @DisplayName("O campo genero deve estar preenchido")
    void campoGeneroDeveSerPreenchidoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Masculino", cadastroTest.getGenero());

    }

    @Test
    @DisplayName("O campo genero deve aceitar '-'")
    void campoGeneroDeveAceitarTraço(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Ariel",
                "ariel",
                "ariel@gmail.com",
                "48720863849", "13-02-2002",
                "Não-Binário", "Abcd@1234", "Abcd@1234");

        assertEquals("Não-Binário", cadastroTest.getGenero());

    }

    @Test
    @DisplayName("O campo senha deve estar preenchido")
    void campoSenhaDeveSerPreenchidoNoConstrutor(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Abcd@1234", cadastroTest.getSenha());

    }

    @Test
    @DisplayName("O campo senha deve conter no minimo 8 caracteres")
    void campoSenhaDeveTerNoMinimo8Caracteres(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Abcd@1234", cadastroTest.getSenha());

    }

    @Test
    @DisplayName("O campo senha deve conter no minimo 8 caracteres")
    void campoSenhaDeveSerDigitadoNoAlfabetoLatino(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Abcd@1234", cadastroTest.getSenha());

    }

    @Test
    @DisplayName("O campo senha deve conferir com confirmar senha")
    void campoSenhaDeveConferirConfirmarSenha(){

        UsuarioCadastroSimples cadastroTest = new UsuarioCadastroSimples("Dylan Colonhesi",
                "dylancolonhesi",
                "colonhesidylan@gmail.com",
                "48720863845", "13-02-2002",
                "Masculino", "Abcd@1234", "Abcd@1234");

        assertEquals("Abcd@1234", cadastroTest.getSenha());
        assertEquals("Abcd@1234", cadastroTest.getConfirmarSenha());

    }

}