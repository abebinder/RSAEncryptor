
import java.math.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GUIController {
	PublicKeyEncryption keys;
	BigInteger n;
	BigInteger e;
	BigInteger d;
	
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
	void generateButtonPressed(ActionEvent event){
		keys=new PublicKeyEncryption();
		n=keys.getN();
		e=keys.getPublicKey();
		d=keys.getPrivateKey();
		String nAsString=n.toString();
		String eAsString=e.toString();
		String dAsString=d.toString();
		NTextArea.setText(nAsString);
		ETextArea.setText(eAsString);
		DTextArea.setText(dAsString);
		
	}
	@FXML
	void EncryptButtonPressed(ActionEvent event){
		String localNString=NTextArea.getText();
		String localPublicKeyString=ETextArea.getText();
		String toEncryptString=encryptThisMessage.getText();
		BigInteger localN=new BigInteger(localNString);
		BigInteger localPublicKey= new BigInteger(localPublicKeyString);
		BigInteger toEncrypt=new BigInteger(toEncryptString);
		BigInteger encrypted=keys.encrypt(toEncrypt, localPublicKey, localN);
		String encryptedString=encrypted.toString();
		encryptedMessage.setText(encryptedString);
	}
	
	@FXML
	void decryptButtonPressed(ActionEvent event){
		String localNString=NTextArea.getText();
		String localPrivateKeyString=DTextArea.getText();
		String toDecryptString=decryptThisMessage.getText();
		BigInteger localN=new BigInteger(localNString);
		BigInteger localPrivateKey=new BigInteger(localPrivateKeyString);
		BigInteger toDecrypt=new BigInteger(toDecryptString);
		BigInteger decrypted=keys.decrypt(toDecrypt, localPrivateKey, localN);
		String decryptedString=decrypted.toString();
		decryptedMessage.setText(decryptedString);
	}
	
}
