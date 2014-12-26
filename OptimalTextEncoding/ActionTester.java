import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;



public class ActionTester
{
	static HuffTree finished;
	public static void main(String[] args)
	{
		
		JFrame frame = new JFrame();
		final JDialog tree = new JDialog(); 
		
		tree.setSize(new Dimension(1000, 500));
//		tree.setResizable(false);


		final int FIELD_WIDTH = 20;
		final JTextField textField = new JTextField(FIELD_WIDTH);
		final JTextField huffOutput = new JTextField(FIELD_WIDTH);
		textField.setText("Enter some plain text!");
		huffOutput.setText("Here will be your Huffington's code!");

		final JTextField huffField = new JTextField(FIELD_WIDTH);
		final JTextField textOutput = new JTextField(FIELD_WIDTH);
		huffField.setText("Enter some Huffman's code!");
		textOutput.setText("Here will be your text!");

		JButton toTextButton = new JButton("Get Text");
		toTextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String huffFieldValue = huffField.getText();
				String output =finished.huffToText(huffFieldValue);
				
				
				textOutput.setText(output);
				//TODO: manipulate textfield value and then output huffcode (use hash table)
			}
		});


		JButton toHuffButton = new JButton("Get Huffman");
		toHuffButton.addActionListener(new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent ae){
				@SuppressWarnings("rawtypes")
				SeparateChainingHashTable hashTable = new SeparateChainingHashTable(true);
				String textFieldValue = textField.getText();
				char[] cArray= textFieldValue.toCharArray();
				for(int i=0; i<cArray.length; i++){
					CharAndWeight x = new CharAndWeight((Character) cArray[i]);
					hashTable.insert(x);
				}

				HuffTree[] newTrees = new HuffTree[199];

				for(int i = 0; i<hashTable.listLength(); i++){
					@SuppressWarnings("unchecked")
					List<CharAndWeight>[] list = hashTable.list();
					List<CharAndWeight> whichList =  list[i];
					ListIterator<CharAndWeight> j = whichList.listIterator();
					for(int k=0; k<newTrees.length; k++){
						while(whichList!=null &&j.hasNext()){
							CharAndWeight element = j.next();
							newTrees[i]= new HuffTree(element);
						}
					}
				}

				@SuppressWarnings("rawtypes")
				BinaryHeap heap = new BinaryHeap(newTrees);

				finished = null;
				while(!heap.isEmpty()){
					HuffTree a = (HuffTree) heap.deleteMin();
					if(!heap.isEmpty()){
						HuffTree b=(HuffTree) heap.deleteMin();
						HuffTree joined = new HuffTree(a, b);
						heap.insert(joined);
					}
					else{
						finished=a;
					}
				}

				CharAndWeight[] toTable = HuffTree.updateCodeArray(finished.root()); 
				@SuppressWarnings("rawtypes")
				SeparateChainingHashTable codeTable = new SeparateChainingHashTable(false);

				for(int i =0; i<toTable.length; i++){

					CharAndWeight x = toTable[i];
					codeTable.insert(x);

				}

				//				codeTable.printTable();

				String huffCode="";

				//create hash table of unique characters and their weight

				char[] carArray= textFieldValue.toCharArray();
				for(int i=0; i<carArray.length; i++){

					huffCode+=codeTable.findCode(carArray[i]);


				}

				huffOutput.setText(huffCode);
				//TODO: manipulate textfield value and then output huffcode
			}
		});

		JButton showTree = new JButton("Show Huffman Tree!");
		showTree.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(!tree.isVisible()){
					HuffNodeComponent jess = new HuffNodeComponent( new HuffNodeShape(500,0,20, finished.root()));
					jess.setPreferredSize(new Dimension(150,100));
					JScrollPane scroll = new JScrollPane(jess, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					tree.add(scroll);
					
					tree.setVisible(true);
				}
			}
		});






		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		frame.add(textField);
		frame.add(toHuffButton);
		frame.add(huffOutput);
		frame.add(huffField);
		frame.add(toTextButton);
		frame.add(textOutput);
		frame.add(showTree);
		//frame.add(tree);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


}
