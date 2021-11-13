package test;

import java.util.ArrayList;   
import java.util.List;   
import java.util.concurrent.*;   

/**
 * https://programmerclick.com/article/4694667705/
 * @author YFigueroa
 *
 */
public class CallableDemo{   
    public static void main(String[] args){   
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Respuesta>> resultList = new ArrayList<Future<Respuesta>>();   
  
                 // Crea 10 tareas y ejecuta   
        for (int i = 0; i < 100; i++){   
                         // Use ExecutorService para realizar tareas invocables y guardar el resultado en la variable futura   
            Future<Respuesta> future = executorService.submit(new TaskWithResult(i));   
                         // Almacenar resultados de ejecuci�n de tareas en Lista   
            resultList.add(future);   
        }   
  
                 // El resultado de atravesar la tarea   
        for (Future<Respuesta> fs : resultList){   
                try{   
                      while (! fs.isDone()); // Future devuelve Si no se completa, esperar� en un bucle hasta que Future vuelva a completarse  
                      System.out.println (fs.get()); // Imprime el resultado de la ejecuci�n de cada hilo (tarea) 
                      
                }catch(InterruptedException e){   
                    e.printStackTrace();   
                }catch(ExecutionException e){   
                    e.printStackTrace();   
                }finally{   
                                         // Inicie un apagado secuencial, ejecute las tareas enviadas anteriormente, pero no acepte nuevas tareas  
                    executorService.shutdown();   
                }   
        }   
    }   
}   
  
  
class TaskWithResult implements Callable<Respuesta>{   
    private int id;   
  
    public TaskWithResult(int id){   
        this.id = id;   
    }   
  
    /**  
           * El proceso espec�fico de la tarea, una vez que la tarea se pasa al m�todo de env�o de ExecutorService, 
           * Entonces el m�todo se ejecuta autom�ticamente en un hilo 
     */   
//    public String call() throws Exception {  
    public Respuesta call() throws Exception {
                 System.out.println ("el m�todo call () se llama autom�ticamente !!!" + Thread.currentThread ().getName());   
                 // El resultado devuelto se obtendr� por el m�todo get de Future  
                 //El m�todo 
//                 return "call () se llama autom�ticamente y el resultado que devuelve la tarea es:" + id + "" + Thread.currentThread ().getName(); 
               //se bloque el hilo un tiempo aleatorio
                int time = (int)(Math.random()*400+100);
         		try {
//         			time = (int)(Math.random()*400+100); 
         			System.out.println(time);
         			Thread.sleep(time);
         		} catch (InterruptedException e) {
         			System.out.println(e.getMessage());
         		}
                 return new Respuesta(this.id, "va", time);
    }   
}

class Respuesta {
	private int id;
	private String str;
	private int time;
	
	public Respuesta(int argId, String argStr, int argTime) {
		this.setId(argId);
		this.setStr(argStr);
		this.setTime(argTime);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	

	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", str=" + str + ", time=" + time + "]";
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
	
}
