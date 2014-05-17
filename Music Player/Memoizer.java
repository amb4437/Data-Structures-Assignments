import java.util.HashMap;

public class Memoizer {

	private HashMap hashMap;
	private Functor function;


	public Memoizer(Functor f){

		hashMap = new HashMap<Object, Object>();
		function = f;


	}

	public Object call(Object x){
		
		Object answer;
		if (hashMap.containsKey(x))
			return x;
		
		answer = function.fn(x);
		hashMap.put(x, answer);
		return answer;
	}





}
