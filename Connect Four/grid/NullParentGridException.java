package grid;


/**
 * NullParentGridException is thrown when a method invoked on a Box or GridItem breaks because
 * it needs to act on a parent Grid, but its parent Grid has never been set.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see Grid#setBoxAt(Box, int, int)
 * @see Grid#setBoxes(Box[][])
 */
@SuppressWarnings("serial")
public class NullParentGridException extends GridException
{

}
