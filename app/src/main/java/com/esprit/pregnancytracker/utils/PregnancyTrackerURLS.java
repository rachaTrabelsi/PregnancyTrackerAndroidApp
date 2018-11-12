package com.esprit.pregnancytracker.utils;

/**
 * Created by a on 22/12/2017.
 */

public class PregnancyTrackerURLS {

    public PregnancyTrackerURLS() {
    }
    public static  String MyIPAddress ="41.226.11.243:10080/pregnancytracker";

    public static String URLSERVICE = "http://" + MyIPAddress + "/PregnancyTrackerServices/CRUDRendezVous/insertRendezVous.php?";
    public static String URL_SERVICE_UPDATE_RENDEZ_VOUS = "http://" + MyIPAddress + "/PregnancyTrackerServices/CRUDRendezVous/updateRendezVous.php?";


    public static  String  URLSERVICELISTPushFCM = "http://" +MyIPAddress + "/PregnancyTrackerServices/PushNotificationScript.php";
    public static  String  URLSERVICELISTgetFCMtoken= "http://" + MyIPAddress + "/PregnancyTrackerServices/getExistedTokens.php?";
    public static  String  URL_SERVICE_INSERT_FCMtoken= "http://" + MyIPAddress + "/PregnancyTrackerServices/InsertFCMToken.php?";


    public static String URLSERVICERendezVousMedecin= "http://"+ MyIPAddress+"/PregnancyTrackerServices/getMedecin.php?";


    public static String URLSERVICE_SELECT_NOTIFICATION= "http://"+ MyIPAddress+"/PregnancyTrackerServices/selectNotification.php?";

/******************************Medecin***************/
public static String URL_SERVICE_GET_Medecin= "http://"+ MyIPAddress+"/PregnancyTrackerServices/getMedecin.php?";
   public static String URL_SERVICE_IS_MEdecin= "http://"+ MyIPAddress+"/PregnancyTrackerServices/MedecinService/MedecinLogin.php?";
    public static String URL_SERVICE_GET_MEDECIN= "http://"+UrlAddress.MyIPAddress+"/PregnancyTrackerServices/getMedecin.php?";
    public static String URL_SERVICE_GET_MEdecin_BY_EMAIL= "http://"+ MyIPAddress+"/PregnancyTrackerServices/MedecinService/MedecinGetBYemail.php?";
    public static String URL_SERVICE_LOGIN_MEDECIN  = "http://"+ MyIPAddress+"/PregnancyTrackerServices/MedecinService/MedecinLogin.php?";

    //\PregnancyTrackerServices\MedecinService/MedecinLogin.php
   /*************************************************/


/********************************DebutPAtiente***********************************************************/
    public static String URLSERVICE_UPDATE_PATIENTE_PROFIL = "http://" + MyIPAddress + "/PregnancyTrackerServices/updatePatiente.php?";
    public static  String URLSERVICE_GET_PATIENTE = "http://"+   MyIPAddress+"/PregnancyTrackerServices/getPatiente.php?";
    public static  String URLSERVICE_UPDATE_IMG_PATIENTE = "http://"+  MyIPAddress+"/PregnancyTrackerServices/updatePicturePatiente.php";
    public static  String URLSERVICE_LOGIN_PATIENTE = "http://"+  MyIPAddress+"/PregnancyTrackerServices/loginforuser.php";
    public static  String URLSERVICE_REGISTER_PATIENTE = "http://"+  MyIPAddress+"/PregnancyTrackerServices/registeruser.php";
    public static  String URLSERVICE_GET_PATIENTE_BY_Doctor = "http://"+  MyIPAddress+"/PregnancyTrackerServices/getPatientesByMedecin.php?";

    /********************************INFOSPATIENTE****************/
     public static  String URLSERVICE_SELECT_SPORT = "http://"+  MyIPAddress+"/PregnancyTrackerServices/selectSport.php";
    public static  String URLSERVICE_SELECT_SLEEP = "http://"+  MyIPAddress+"/PregnancyTrackerServices/selectSleep.php";
    public static  String URLSERVICE_SELECT_WATER = "http://"+  MyIPAddress+"/PregnancyTrackerServices/selectWater.php";

        /*****************************INFOSPATIENTE*************************/
        public static  String URLSERVICE_UPDATE_WEEK = "http://"+  MyIPAddress+"/PregnancyTrackerServices/updateweek.php?";


    /****************************************Fin Patiente****************************************************/
    public static String URL_Token_NOTIFICATION = "http://" + MyIPAddress + "/PregnancyTrackerServices/PushNotificationScript.php?";

    public static String URL_GET_DOCTOR_PATIENTE = "http://" + MyIPAddress + "/PregnancyTrackerServices/getMedecin.php?";

    public static String URLSERVICEMEdecin = "http://" +   MyIPAddress + "/PregnancyTrackerServices/MedecinService/getAllMedecin.php";
/***********************Message**********************************/
      public static String URLSERVICE_INSERT_MESSAGE = "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDMessage/InsertMessage.php?";
    public static String URLSERVICE_Get_MESSAGE_BY_PATIENTE = "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDMessage/getMessagebyPatiente.php?";
    public static String URLSERVICE_Get_MESSAGE_BY_MEDECIN = "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDMessage/getMessagesByDoctor.php?";
    public static String URLSERVICE_DELETE_MESSAGE = "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDMessage/deleteMessage.php?";
    public static String URLSERVICE_DELETE_ALL_MESSAGES = "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDMessage/DeleteAllMessages.php";


/***********************FinMessage**********************************/

    /*******DebutCRUDNOte********/
    public static String URLSERVICE_GetNotes_BY_PATIENTE= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDNote/getNotesByPatiente.php?";
    public static String URLSERVICE_INSERTING_NOTE= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDNote/InsertNote.php?";
    public static String URLSERVICE_DELETING_NOTE= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDNote/deleteNote.php?";
    public static String URLSERVICE_UPDATING_NOTE= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDNote/updateNote.php?";
    public static String URLSERVICE_Get_NOTE_BY_ID= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDNote/getNoteByID.php?";
    public static String URLSERVICE_Delete_ALL_NOTES= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDNote/deleteAllNote.php";


    /*******FinCRUDNOte********/

/***********************************Images******************************/
    public static String URL_IMAGE_WEEKS= "http://" +   MyIPAddress + "/PregnancyTrackerImages/Weeks/";
    public static String URL_IMAGE_SPORT_INFORMATIONS = "http://" +   MyIPAddress + "/PregnancyTrackerImages/sportInformation/";
    public static String URL_IMAGE_SLEEP_INFORMATIONS = "http://" +   MyIPAddress + "/PregnancyTrackerImages/sleepInformations/";
    public static String URL_IMAGE_WATERS_INFORMATIONS = "http://" +   MyIPAddress + "/PregnancyTrackerImages/waterInformation/";
    public static String URL_IMAGE_WATERS_SELF_BIDOU = "http://" +   MyIPAddress + "/PregnancyTrackerImages/SelfBidouImages/";
    public static String URL_IMAGES_FOR_NEWS  = "http://" +   MyIPAddress + "/PregnancyTrackerImages/medecinNews/";
    public static String URL_IMAGES_IMAGES_TO_EAT  = "http://" +   MyIPAddress + "/PregnancyTrackerImages/Imagestoeat/";
    public static String URL_IMAGES_IMAGES_NOT_TO_EAT  = "http://" +   MyIPAddress + "/PregnancyTrackerImages/Imagesnottoeat/";
    public static String URL_IMAGES_PATIENTES  = "http://" +   MyIPAddress + "/PregnancyTrackerImages/";


/****************************************************************************/


    public static String URL_SERVICE_INFO_NOT_TO_EAT = "http://" +   MyIPAddress + "/PregnancyTrackerServices/infonottoeat.php";
    public static String URL_SERVICE_INFO_TO_EAT = "http://" +   MyIPAddress + "/PregnancyTrackerServices/selecttoeat.php";
    public static String URL_INSERT_Etat= "http://" +   MyIPAddress + "/PregnancyTrackerServices/insertEtat.php";
    public static String URL_SELECT_IDEAL= "http://" +   MyIPAddress + "/PregnancyTrackerServices/selectIdeal.php";

    /*******************Self Bidou***********************/
    public static String URL_SERVICE_GET_SELFBIDOU_BY_PATIENTE= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDSELFBIDOU/GetSefBidouByPatiente.php?";
    public static String URL_SERVICE_INSERT_SELFBIDOU_BY_PATIENTE= "http://" +   MyIPAddress + "/PregnancyTrackerServices/CRUDSELFBIDOU/InsertSelfies.php?";

    /****************************************************/
    /**********************NewsForDoctor******************************/

    public static String URL_SERVICE_GET_NEWS_FOR_DOCTOR= "http://" +   MyIPAddress + "/PregnancyTrackerServices/MedecinService/getAllNewsForMedecin.php";

    /*****************************************************************/

    public static String URL_SERVICE_SHOWRENDEZVOUS_IF_EXISTS =  "http://"+ MyIPAddress+"/PregnancyTrackerServices/getRendezVousByPatiente.php?";


}
