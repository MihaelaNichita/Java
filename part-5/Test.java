
public class Test {

	public static void main(String[] args) {
		MintoNinserters.testInsertMintoN();
		BinaryDoubleToString.testBinaryDoubleToString();	

		OnesSeqMaxLenght.testMaxLengthFlipBit(159365884);
		OnesSeqMaxLenght.testMaxLengthFlipBit(85638485);
		OnesSeqMaxLenght.testMaxLengthFlipBit(-935673498);
		
		GetNextPrevNumbersWithSameNumberOfOnes.testGetNextAndPrev(99999);		
		int n1=(~0<<30);
		int n2=(~0>>>1); // because ~0 is negative => >>> (logical shift) : MSB=zero
		int n=n1&n2;
		GetNextPrevNumbersWithSameNumberOfOnes.testGetNextAndPrev(n);
	
		NumOfBitsToFlipToConvertIntAtoIntB.testNumOfBitsToFlip();
		OddEvenBitsSwapper.testSwapOddEvenBits();
		LineDrawer.testDrawLine();
	}
}




