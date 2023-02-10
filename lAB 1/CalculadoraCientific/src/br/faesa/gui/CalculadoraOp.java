package br.faesa.gui;

public class CalculadoraOp {
	private static StringBuffer operando = new StringBuffer();
	private static StringBuffer expressao = new StringBuffer();
	private static String operacao;
	private static String digito;
	private static Double resultado;
	private static String[] vExpressao=new String[1];
	private static Double[] vOperacao=new Double[1];
	private static String[] bExpressao;
	private static Double[] bOperacao;
	private static String Mensage;
	private static int count=0;
	
	private static int pFechado;
	
	public static void coletaOperando(){
		operando.append(digito);
		vOperacao[count]=Double.valueOf(operando.substring(0));
		expressao.append(digito);
	}
	
	public static void montaExpressao(){
		expressao.append(operacao);
		vExpressao[count]=operacao.substring(0);
		operando = new StringBuffer("");
		backupOperacao();
		backupExpressao();
		count++;
	}
	
	public static void excluirExpressao(){
		expressao = new StringBuffer("");
		operando = new StringBuffer("");
		vExpressao = new String[1];
		vOperacao = new Double[1];
		count=0;
	}	
	
	public static void Negativar(){
			expressao.delete(expressao.length()-operando.length(), expressao.length());
			double negativar=Double.valueOf(operando.substring(0, operando.length()))*(-1);
			operando = new StringBuffer(String.valueOf(negativar));
			vOperacao[count]=Double.valueOf(operando.substring(0));
			expressao.append(operando.substring(0));
	}
	
	public static void Inverte(){
		expressao.delete(expressao.length()-operando.length(), expressao.length());
		double inverte=1/(Double.valueOf(operando.substring(0, operando.length())));
		vOperacao[count]=inverte;
		operando = new StringBuffer(String.valueOf(vOperacao[count]));
		expressao.append(operando.substring(0));
}
	public static void Primo(){
	Boolean Primo=true;
	for (int i = 2; i < vOperacao[count]; i++) {
			if(vOperacao[count]%i==0){
				Primo=false;
			}
		}
		double rest=vOperacao[count];
		if(vOperacao[count]-(int)rest>0 || vOperacao[count]<0){
			setMensage("Número inválido");
		}
		else if(Primo){
			setMensage("É primo");
		}
		else{
			setMensage("Não é primo");
		}
	}

	public static void efetuaResultado(){
		efetuaCalculo(0, vExpressao.length-1);
		int n=0;
		while(vOperacao[n] == null){
			n++;
		}
		
		resultado=vOperacao[n];
		System.out.println("resultado: "+resultado);
		vExpressao = new String[1];
		vOperacao = new Double[1];
		count=0;
		if(String.valueOf(resultado)=="Infinity"){
			setMensage("Divisão por zero!");
			excluirExpressao();
			return;
		}
		if(String.valueOf(resultado)=="NaN"){
			setMensage("Resultado Inválido!");
			excluirExpressao();
			return;
		}
		setMensage(String.valueOf(resultado));
		operando = new StringBuffer(String.valueOf(resultado));
		expressao = new StringBuffer(String.valueOf(resultado));
		vOperacao[0] = resultado;
	}
	
	private static void efetuaCalculo(int x, int y){
		int j=1;
		int k=0;
		for (int i = x; i < y; i++) {
			boolean inside=false;
			if(vExpressao[i] == "("){
				pFechado=1;
				while(vExpressao[i+pFechado]!=")"&&!inside){
					if(vExpressao[i+pFechado] == "("){
						inside=true;
					}
					pFechado++;
				}
				if(inside){
					pFechado=1;
					while(vExpressao[vExpressao.length-pFechado]!=")"){
						pFechado++;
					}
					vExpressao[vExpressao.length-pFechado]=null;
					vExpressao[i]=null;
					efetuaCalculo(i+1,vExpressao.length-pFechado);
					if(vOperacao[vExpressao.length-pFechado+1]==null){
						vOperacao[vExpressao.length-pFechado+1]=vOperacao[vExpressao.length-pFechado];
						vOperacao[vExpressao.length-pFechado]=null;
					}
					else{
						vExpressao[vExpressao.length-pFechado]=vExpressao[vExpressao.length-pFechado+1];
						vExpressao[vExpressao.length-pFechado+1]=null;
					}
				}else{
					vExpressao[i+pFechado]=null;
					vExpressao[i]=null;
					efetuaCalculo(i+1,i+pFechado);
					if(vOperacao[i+pFechado+1]==null){
						vOperacao[i+pFechado+1]=vOperacao[i+pFechado];
						vOperacao[i+pFechado]=null;
					}
					else{
						vExpressao[i+pFechado]=vExpressao[i+pFechado+1];
						vExpressao[i+pFechado+1]=null;
					}
				}
			}
		}
		for (int i = x; i < y; i++) {
			if(vExpressao[i] == "bLog"){
				while(vOperacao[i+j]==null){
					j++;
				}
				while(vOperacao[i-k]==null){
					k++;
				}
				vOperacao[i+j]=logarithmFunction(vOperacao[i-k],vOperacao[i+j]);
				vOperacao[i-k]=null;
				vExpressao[i]=null;
				j=1;
				k=0;
			}
		}
		for (int i = x; i < y; i++) {
			if(vExpressao[i] == "^" || vExpressao[i] == "\u221A"){
				while(vOperacao[i+j]==null){
					j++;
				}
				while(vOperacao[i-k]==null){
					k++;
				}
				vOperacao[i+j]=exponentFunction(vOperacao[i-k],vOperacao[i+j],vExpressao[i]);
				vOperacao[i-k]=null;
				vExpressao[i]=null;
				j=1;
				k=0;
			}
		}
		for (int i = x; i < y; i++) {
			if(vExpressao[i] == "X" || vExpressao[i] == "/"){
				while(vOperacao[i+j]==null){
					j++;
				}
				while(vOperacao[i-k]==null){
					k++;
				}
				vOperacao[i+j]=multdivFunction(vOperacao[i-k],vOperacao[i+j],vExpressao[i]);
				vOperacao[i-k]=null;
				vExpressao[i]=null;
				j=1;
				k=0;
			}
		}
		for (int i = x; i < y; i++) {
			if(vExpressao[i] == "+" || vExpressao[i] == "-"){
				while(vOperacao[i+j]==null){
					j++;
				}
				while(vOperacao[i-k]==null){
					k++;
				}
				vOperacao[i+j]=addsubFunction(vOperacao[i-k],vOperacao[i+j],vExpressao[i]);
				vOperacao[i-k]=null;
				vExpressao[i]=null;
				j=1;
				k=0;
			}
		}
	}
	
	private static double exponentFunction(Double v1, Double v2, String expression){
		double result;
		switch(expression){
		case "^":
			result = Math.pow(v1, v2);
			break;
		case "\u221A":
			result = Math.pow(v2, 1/v1);
			break;
		default:
			result = 0;
			break;
		}					
		return result;
	}
	
	private static double logarithmFunction(Double v1, Double v2){
		double result;
		result = Math.log10(v1)/Math.log10(v2);					
		return result;
	}

	
	
	private static double multdivFunction(Double v1, Double v2, String expression){
		double result;
		switch(expression){
		case "X":
			result = v1 * v2;
			break;
		case "/":
			result = v1 / v2;
			break;
		default:
			result = 0;
			break;
		}										
		return result;
	}
	
	private static double addsubFunction(Double v1, Double v2, String expression){
		double result;
		switch(expression){
		case "+":
			result = v1 + v2;
			break;
		case "-":
			result = v1 - v2;
			break;
		default:
			result = 0;
			break;
		}					
		return result;
	}
	
	private static void backupOperacao(){
		bOperacao=vOperacao;
		int tam=bOperacao.length+1;
		vOperacao=new Double[tam];
		for (int i = 0; i < bOperacao.length; i++) {
			vOperacao[i]=bOperacao[i];
		}
		bOperacao=new Double[vOperacao.length];
	}
	
	private static void backupExpressao(){
		bExpressao=vExpressao;
		int tam=bExpressao.length+1;
		vExpressao=new String[tam];
		for (int i = 0; i < bExpressao.length; i++) {
			vExpressao[i]=bExpressao[i];
		}
		bExpressao=new String[vExpressao.length];
	}
	
	// getters & setters	
	public static StringBuffer getOperando() {
		return operando;
	}
	public static void setOperando(StringBuffer operando) {
		CalculadoraOp.operando = operando;
	}
	public static StringBuffer getExpressao() {
		return expressao;
	}
	public static void setExpressao(StringBuffer expressao) {
		CalculadoraOp.expressao = expressao;
	}
	public static String getOperacao() {
		return operacao;
	}
	public static void setOperacao(String operacao) {
		CalculadoraOp.operacao = operacao;
	}
	public static String getDigito() {
		return digito;
	}
	public static void setDigito(String digito) {
		CalculadoraOp.digito = digito;
	}
	public static Double getResultado() {
		return resultado;
	}
	public static void setResultado(Double resultado) {
		CalculadoraOp.resultado = resultado;
	}

	public static String getMensage() {
		return Mensage;
	}

	public static void setMensage(String mensage) {
		Mensage = mensage;
	}
}
