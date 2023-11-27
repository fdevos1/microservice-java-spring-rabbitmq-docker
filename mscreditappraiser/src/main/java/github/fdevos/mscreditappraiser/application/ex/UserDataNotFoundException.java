package github.fdevos.mscreditappraiser.application.ex;

public class UserDataNotFoundException extends  Exception {
    public UserDataNotFoundException() {
        super("Dados do cliente não encontrado para o CPF informado");
    }
}
