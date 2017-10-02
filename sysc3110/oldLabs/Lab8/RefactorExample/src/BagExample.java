public class BagExample
{
	void processMessage(String msg)
	{
		IBag bag = new BagImpl();
		bag.set(msg);
		MessagePipe pipe = new MessagePipe();
		pipe.send(bag);
	}
}