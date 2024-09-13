package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){

        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers Selenium\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Maximizar a tela
        this.navegador.manage().window().maximize();

        // Definir tempo de espera padrao de 5s
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a pagina da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){

        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        //Vou validar que a mensagem de erro foi apresentada
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
        System.out.println(mensagemApresentada);

    }

    @Test
    @DisplayName(" Nao e permitido registrar um produto com valor maior que 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7.000,00")
    public void testPossoAdicionarProdutosComValorDeUmcentavoASeteMilReais(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MacBook Pro")
                .informarValorDoProduto("30000")
                .informarCoresDoProduto("Cinza")
                .submeterFormularioDeEdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach(){

        // Fechar o navegador
        navegador.quit();
    }

}
