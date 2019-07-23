package it.alex.lab11;

public class ExceptionCalculator extends RuntimeException {

    public ExceptionCalculator (){
        super();

    }public ExceptionCalculator (RuntimeException message){
        super(message);
    }


}
