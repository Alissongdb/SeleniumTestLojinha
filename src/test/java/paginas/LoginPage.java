package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //Atributo da classe do tipo Webdriver
    private WebDriver navegador;

    //Construtor da classe
    public LoginPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public LoginPage informarOUsuario(String usuario){

        navegador.findElement(By.cssSelector("label[for='usuario']"));
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        return this;

    }

    public LoginPage informarASenha(String senha){

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys(senha);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeLogin(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }

}
