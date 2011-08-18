/**
 * 
 */
package obsolete;

import java.util.ArrayList;

/**
 * @author kto
 * @obsolete
 */
public class ArrayList2D<Type> {
	ArrayList<ArrayList<Type>>	array;
	 
	public ArrayList2D()
	{
		array = new ArrayList<ArrayList<Type>>();
	}
 
	/**
	 * ensures a minimum capacity of num rows. Note that this does not guarantee
	 * that there are that many rows.
	 * 
	 * @param num
	 */
	public void ensureCapacity(int num)
	{
		array.ensureCapacity(num);
	}
 
	/**
	 * Ensures that the given row has at least the given capacity. Note that
	 * this method will also ensure that getNumRows() >= row
	 * 
	 * @param row
	 * @param num
	 */
	public void ensureCapacity(int row, int num)
	{
		ensureCapacity(row);
		while (row < getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).ensureCapacity(num);
	}
 
	/**
	 * Adds an item at the end of the specified row. This will guarantee that at least row rows exist.
	 */
	public void Add(Type data, int row)
	{
		ensureCapacity(row);
		while(row >= getNumRows())
		{
			array.add(new ArrayList<Type>());
		}
		array.get(row).add(data);
	}
 
	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Type get(int row, int col)
	{
		return array.get(row).get(col);
	}
 
	/**
	 * 
	 * @param row
	 * @param col
	 * @param data
	 */
	public void set(int row, int col, Type data)
	{
		array.get(row).set(col,data);
	}
 
	/**
	 * 
	 * @param row
	 * @param col
	 */
	public void remove(int row, int col)
	{
		array.get(row).remove(col);
	}
 
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean contains(Type data)
	{
		int sz = array.size();
		for (int i = 0; i < sz; i++)
		{
			if (array.get(i).contains(data))
			{
				return true;
			}
		}
		return false;
	}
 
	/**
	 * 
	 * @return
	 */
	public int getNumRows()
	{
		return array.size();
	}
 
	/**
	 * 
	 * @param row
	 * @return
	 */
	public int getNumCols(int row)
	{
		return array.get(row).size();
	}
}
