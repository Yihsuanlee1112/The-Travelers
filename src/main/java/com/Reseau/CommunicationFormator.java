package com.Reseau;

public class CommunicationFormator implements CommunicationFormatorIT {    
    /** 
     * Format join instruction
     * @param GroupCode
     * @return String
     */
    @Override
    public String join(String GroupCode) {
        return "01" + GroupCode;
    }

    
    /** 
     * @param GroupCode
     * @return String
     * Format leave instruction
     */
    @Override
    public String leave(String GroupCode) {
        return "02" + GroupCode;
    }

    
    /** 
     * @param Message
     * @param GroupCode
     * @return String
     * Format sending message instruction
     */
    @Override
    public String send(String Message, String GroupCode,String Username) {
        if(Username.length()<8){
            for (int i =Username.length();i<8;i++){
                Username=Username+":";
            }
        }
        return "03" + GroupCode + Username+Message;
    }

    
    /** 
     * @return String
     * Format connection instruction
     */
    @Override
    public String connect() {
        return "00";
    }

    
    /** 
     * @return String
     * Format disconnection instruction
     */
    @Override
    public String disconnect() {
        return "FF";
    }
}