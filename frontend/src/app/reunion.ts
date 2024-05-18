import {User} from "./user";

export class Reunion {
  id_Re! :any;
  id_user! :User;
  debutR! :Date;
  finReu! : Date;
  titre! :string;
  description!:string;
  nom_rapporteur!:string;
  nom_organisateur:string ="";
  nom_salle:string =" ";
  ids : number[] = [];
  //ID_user!:User[];
  //rapport!:string;
  //rapporteur!:User;
}
