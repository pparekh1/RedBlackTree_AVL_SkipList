public class SkipListEntry 
{
  public String key;
  public Integer value;
  public int pos;      
  public SkipListEntry up, down, left, right;


  public static String negInf = new String("-INF");  //Negative Infinity
  public static String posInf = new String("+INF");  //Positive Infinity

  public SkipListEntry(String k, Integer v) 
  { 
     key = k;
     value = v;
     up = down = left = right = null;
  }

  public Integer getValue()
  { 
	  return value; 
  }

  public String getKey() 
  { 
	  return key; 
  }

  public Integer setValue(Integer val) 
  {
    Integer oldValue = value;
    value = val;
    return oldValue;
  }
  
  public String toString() 
  { 
	  return "(" + key + "," + value + ")"; 
  }
  
  public boolean equals(Object o) 
  {
    SkipListEntry ent;
    try { ent = (SkipListEntry) o; }
    catch (ClassCastException ex) { return false; }
    return (ent.getKey() == key) && (ent.getValue() == value);
  }
}