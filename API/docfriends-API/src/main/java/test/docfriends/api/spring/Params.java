package test.docfriends.api.spring;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.CaseInsensitiveMap;

/**
 * 
 * SqlMap.java
 *
 * <pre>
 * SQL Map 
 * </pre>
 *
 * @author harne 
 * @date 2019. 4. 9.
 */
public class Params { // implements Map<String, Object> {
	
	public enum Type {
		Object, Params, Map, List, String, Boolean, Double, Long, Integer
	}

	private Map<String, Object> parameters = null;
	
	@SuppressWarnings("unchecked")
	public Params() {
		parameters = new CaseInsensitiveMap();
	}

	public Map<String, Object> getMap() {
		return parameters;
	}

	/*@Override*/ public int     size()                        { return parameters.size();               }
	/*@Override*/ public boolean isEmpty()                     { return parameters.isEmpty();            }
	/*@Override*/ public boolean containsKey(Object key)       { return parameters.containsKey(key);     }
	/*@Override*/ public boolean containsValue(Object value)   { return parameters.containsValue(value); }
	/*@Override*/ public Object  put(String key, Object value) { return parameters.put(key, value);      }
	/*@Override*/ public Object  get(Object key)               { return parameters.get(key);    }
	/*@Override*/ public Object  remove(Object key)            { return parameters.remove(key); }
	/*@Override*/ public void    putAll(Map<? extends String, ? extends Object> m) { parameters.putAll(m); }
	/*@Override*/ public void    clear()                       { parameters.clear();           }
	/*@Override*/ public Set<String>                keySet()   { return parameters.keySet();   }
	/*@Override*/ public Collection<Object>         values()   { return parameters.values();   }
	/*@Override*/ public Set<Map.Entry<String, Object>> entrySet() { return parameters.entrySet(); }
	public void putAll(Params m) { parameters.putAll(m.parameters); }
	
	public boolean exist(String key) {
		return (parameters.get(key) != null);
	}
	
	public Type typeOf(Object value) {
		if (value == null) {
			return null;
		} else if (value instanceof Params) {
			return Type.Params;
		} else if (value instanceof Map) {
			return Type.Map;
		} else if (value instanceof List) {
			return Type.List;
		} else if (value instanceof String) {
			return Type.String;
		} else if (value instanceof Boolean) {
			return Type.Boolean;
		} else if (value instanceof Double) {
			return Type.Double;
		} else if (value instanceof Long) {
			return Type.Long;
		} else if (value instanceof Integer) {
			return Type.Integer;
		} else {
			return Type.Object;
		}
	}
	public Type typeOf(String key) {
		return typeOf(get(key));
	}
	
	@SuppressWarnings("rawtypes")
	public Params getMap(String key) {
		Object value = get(key);
		if (value == null) return null;
		switch (typeOf(value)) {
			case Params:
				return (Params) value;
			case Map:
				return toSqlMap((Map) value);
			default:
				return null;
		}
	}
	public static Params toSqlMap(@SuppressWarnings("rawtypes") Map map) {
		Params result = new Params();
		for (Object k : map.keySet()) {
			result.put(k.toString(), map.get(k));
		}
		return result;
	}
	@SuppressWarnings("rawtypes")
	public List getList(String key) {
		Object value = get(key);
		if (value == null) return null;
		switch (typeOf(value)) {
			case List:  return (List) parameters;
			default: return null;
		}
	}
	public String getString(String key) {
		Object value = get(key);
		if (value == null) return null;
		return value.toString();
	}
	public Boolean getBoolean(String key) {
		Object value = get(key);
		if (value == null) return null;
		switch (typeOf(value)) {
			case Object:
			case Params:
			case Map:
			case List: return true;
			case String:  return (((String ) value).length() > 0);
			case Boolean: return  ((boolean) value);
			case Double:  return (((double ) value) != 0);
			case Long:    return (((long   ) value) != 0);
			case Integer: return (((int    ) value) != 0);
			default: return false;
		}
	}
	public Double getDouble(String key) {
		Object value = get(key);
		if (value == null) return null;
		switch (typeOf(value)) {
			case Object:
			case Params:
			case Map:
			case List:
				return 0.0;
			case String:  try {
				return Double.parseDouble((String) value);
			} catch (Exception e) {
				return 0.0;
			}
			case Boolean: return (((boolean) value) ? 1.0 : 0.0);
			case Double:  return (double) ((double ) value);
			case Long:    return (double) ((long   ) value);
			case Integer: return (double) ((int    ) value);
			default: return null;
		}
	}
	public Long getLong(String key) {
		Object value = get(key);
		if (value == null) return null;
		switch (typeOf(value)) {
			case Object:
			case Params:
			case Map:
			case List:
				return 0L;
			case String:  try {
				return Long.parseLong((String) value);
			} catch (Exception e) {
				return 0L;
			}
			case Boolean: return (((boolean) value) ? 1L : 0L);
			case Double:  return (long) ((double ) value);
			case Long:    return (long) ((long   ) value);
			case Integer: return (long) ((int    ) value);
			default: return null;
		}
	}
	public Integer getInteger(String key) {
		Object value = get(key);
		if (value == null) return null;
		switch (typeOf(value)) {
			case Object:
			case Params:
			case Map:
			case List:
				return 0;
			case String:  try {
				return Integer.parseInt((String) value);
			} catch (Exception e) {
				return 0;
			}
			case Boolean: return (((boolean) value) ? 1 : 0);
			case Double:  return (int) ((double ) value);
			case Long:    return (int) ((long   ) value);
			case Integer: return (int) ((int    ) value);
			default: return null;
		}
	}
	
	@Override
	public String toString() {
		return parameters.toString();
	}
	@SuppressWarnings("rawtypes")
	public String jsonValue(Object value) {
		switch (typeOf(value)) {
			case Boolean:
			case Double:
			case Long:
			case Integer:
				return value.toString();
			case Params:
				return ((Params) value).toJson();
			case Map:
				return (toSqlMap((Map) value)).toJson();
			case List: {
				StringBuilder json = new StringBuilder();
				for (Object item : (List) value) {
					if (json.length() == 0) {
						json.append('[');
					} else {
						json.append(jsonValue(item));
					}
					json.append(']');
				}
				return json.toString();
			}
			default:
				break;
		}
		return '"' + value.toString().replace("\"", "\\\"") + '"';
	}
	public String toJson() {
		StringBuilder json = new StringBuilder();
		Set<String> keySet = keySet();
		for (String key : keySet) {
			if (json.length() == 0) {
				json.append('{');
			} else {
				json.append(',');
			}
			json.append('"').append(key.toString().replace("\"", "\\\"")).append('"').append(':').append(jsonValue(get(key)));
		}
		json.append('}');
		return json.toString();
	}
	
	public boolean equals(Params map, String[] keys) {
		for (String key : keys) {
			if (!map.get(key).equals(get(key))) {
				return false;
			}
		}
		return true;
	}
	
	public Params toQuery(String table, String[] columns) {
		put("table", table);
		put("columns", columns);
		return this;
	}
}
