package grid;


/**
 * NullParentBoxException is thrown when a method invoked on a GridItem breaks because it needs to act on
 * its parent Box, but its parent Box has been set to null or has never been set.
 * @author Christopher D'Angelo
 * @author JBD Computers &trade;
 * @see Box#addGridItem(GridItem)
 * @see GridItem#addToBox(Box)
 */
@SuppressWarnings("serial")
public class NullParentBoxException extends GridException
{

}
