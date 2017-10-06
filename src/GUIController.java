import java.math.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/*
 * 
 * When using scenebuilder, it is necessary to make a controller class to handle
 * the codse that actually executes when different
 * things in your gui are activated. Each object here
 * is connected to some object in the fxml file, ad changing
 * it here changes it in the actually gui.d
 */

public class GUIController {
	BigNumberUtils utils=new BigNumberUtils();
	PublicKeyEncryption keys;

	@FXML
	private Button generateButton;

	@FXML
	private Button encryptButton;

	@FXML
	private Button decryptButton;

	@FXML
	private TextArea NTextArea;

	@FXML
	private TextArea ETextArea;

	@FXML
	private TextArea DTextArea;

	@FXML
	private TextArea encryptThisMessage;

	@FXML
	private TextArea encryptedMessage;

	@FXML
	private TextArea decryptThisMessage;

	@FXML
	private TextArea decryptedMessage;

	@FXML
	private CheckBox padNumbers;

	@FXML
	private CheckBox breakApartLarge;

	
	/*
	 * WHen the generate keys button is pressed, it instantiates a new 
	 * publicEncryption object which has unique n, public, and private keys.
	 * It then fills the respected textboxes with those values.
	 */
	@FXML
	void generateButtonPressed(ActionEvent event){
		keys=new PublicKeyEncryption();
		BigInteger n=keys.getN();
		BigInteger e=keys.getPublicKey();
		BigInteger d=keys.getPrivateKey();
		String nAsString=n.toString();
		String eAsString=e.toString();
		String dAsString=d.toString();
		NTextArea.setText(nAsString);
		ETextArea.setText(eAsString);
		DTextArea.setText(dAsString);

	}
	
	/*
	 * WHen encrypt is pressed, it uses the keys in the left textboxes
	 * to encrypt the number entered by the user using c^e mod(n)
	 * If pad is selected, it pads numbers that are too short.
	 */
	@FXML
	void EncryptButtonPressed(ActionEvent event){
		String nAsString=NTextArea.getText();
		String eAsString=ETextArea.getText();
		String toEncryptString=encryptThisMessage.getText();
		BigInteger n=new BigInteger(nAsString);
		BigInteger e= new BigInteger(eAsString);
		BigInteger toEncrypt=new BigInteger(toEncryptString);
		if(padNumbers.isSelected()){

			toEncrypt=keys.pad(toEncryptString);

		}
		BigInteger encrypted=keys.encrypt(toEncrypt, e, n);
		String encryptedString=encrypted.toString();
		encryptedMessage.setText(encryptedString);
	}

	
	/*
	 * WHen decrypt button is pressed it uses the private key on the left
	 * to decrypt what the user has entered using c^d mod n. If depadding is 
	 * selected, it will remove all extra stuff that the padding
	 * algorithim has added to return the message to normal.
	 */
	@FXML
	void decryptButtonPressed(ActionEvent event){
		String nAsString=NTextArea.getText();
		String dAsString=DTextArea.getText();
		String toDecryptString=decryptThisMessage.getText();
		BigInteger n=new BigInteger(nAsString);
		BigInteger d=new BigInteger(dAsString);
		BigInteger toDecrypt=new BigInteger(toDecryptString);
		BigInteger decrypted=keys.decrypt(toDecrypt, d, n);
		String decryptedString="";
		if(padNumbers.isSelected()){
			decryptedString=keys.dePad(decrypted);
		}
		else{
			decryptedString=decrypted.toString();
		}
		decryptedMessage.setText(decryptedString);
	}


}
