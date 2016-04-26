
public class TableEntry implements Comparable<TableEntry>
{
	private int code;
	private String sequence;
	
	public TableEntry (int c, String s)
	{
		code = c;
		sequence = s;
	}
	
	public int getCode() {return code;}
	public String getSequence() {return sequence;}
	
	public void setCode(int c) {code = c;}
	public void setSequence(String s) {sequence = s;}

	@Override
	public int compareTo(TableEntry o) {
		return sequence.compareTo(o.getSequence());
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.compareTo((TableEntry)o) == 0;
	}
	
}
