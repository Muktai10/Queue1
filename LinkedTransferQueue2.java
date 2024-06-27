package queue;


	import java.util.concurrent.LinkedTransferQueue;
	import java.util.concurrent.TransferQueue;

	
	public class LinkedTransferQueue2 {

		public static void main(String[] args) {
			
			TransferQueue<String> packetQueue = new LinkedTransferQueue<>();
			
			
			 
			
			Thread packetProducer = new Thread (() -> {
				
				try
				{
					packetQueue.transfer(" Packet from source A ");
					System.out.println("Packet from source A sent ");
					packetQueue.transfer(" Packet from source B ");
					System.out.println("Packet from source B sent ");
					
				} catch (InterruptedException e)
				{
					Thread.currentThread().interrupt();
					
				}
					
			});
			
			
			
	        Thread packetConsumer = new Thread(() -> {
	            try {
	                String packet = packetQueue.take();
	                System.out.println("Received and routing " + packet);
	                packet = packetQueue.take();
	                System.out.println("Received and routing " + packet);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        });
			
	        packetProducer.start();
	        packetConsumer.start();
	        
	        
	        try {
	            packetProducer.join();
	            packetConsumer.join();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
			
		}

	}

