package csc.summer2019.cscc01.index;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
// https://medium.com/@gtommee97/rest-authentication-with-spring-security-and-mongodb-28c06da25fb1
@Document(collection = "indexer")
public class IndexQA {
  
    @Id
    private ObjectId _id;
    
    private String question;
    
    private String answer;
    
    //private String role;
    
    public IndexQA() {
    }
    
    public void set_id(ObjectId _id) { this._id = _id; }
    
    public String get_id() { return this._id.toHexString(); }
    
    public void setQuestion(String question) { this.question = question; }
    
    public String getQuestion() { return question; }
    
    public void setAnswer(String answer) { this.answer = answer; }
    
    public String getAnswer() { return answer; }
    
}