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
                         // Almacenar resultados de ejecución de tareas en Lista   
            resultList.add(future);   
        }   
  
                 // El resultado de atravesar la tarea   
        for (Future<Respuesta> fs : resultList){   
                try{   
                      while (! fs.isDone()); // Future devuelve Si no se completa, esperará en un bucle hasta que Future vuelva a completarse  
                      System.out.println (fs.get()); // Imprime el resultado de la ejecución de cada hilo (tarea) 
                      
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
           * El proceso específico de la tarea, una vez que la tarea se pasa al método de envío de ExecutorService, 
           * Entonces el método se ejecuta automáticamente en un hilo 
     */   
//    public String call() throws Exception {  
    public Respuesta call() throws Exception {
                 System.out.println ("el método call () se llama automáticamente !!!" + Thread.currentThread ().getName());   
                 // El resultado devuelto se obtendrá por el método get de Future  
                 //El método 
//                 return "call () se llama automáticamente y el resultado que devuelve la tarea es:" + id + "" + Thread.currentThread ().getName(); 
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
