# WS Recherche brevets & CPC (Avec EspaceNet)

## Récupération d'un brevet par son numéro de référence	
- URL : 
    - brevet/api/byReferenceNumber?referenceNumber=FR2944908
- Arguments : 
    - **referenceNumber** (obligatoire) : numéro de référence d'un brevet. Ex : FR2917300, FR2793361, US7370203
- Methode : GET
- Retour : Objet de type BrevetDTO


## Récupération des brevets d'une entreprise par son nom
- URL : 
    - brevet/api/searchByCompany?companyName=LA REGIE AUTONOME DES TRANSPORTS PARISIENS&page=0&cpc=G06&page=0&size=10, 
    - brevet/api/searchByCompany?companyName=Opel Automobile GmbH&cpc=G08

- Arguments : 
    - **companyName** (obligatoire) : nom de l'entreprise pour laquelle on recherche les brevets 
    - **cpc** (optionnel) : Code CPC (Cooperative Patent Classfication). Plus de détails : http://www.cooperativepatentclassification.org/cpcSchemeAndDefinitions/table.html. 
    - **page** (optionnel par défaut 0) : Numéro de la page (Attention commence par 0 donc 1 = 2e page)
    - **size** (optionnel : par defaut 25) : Nombre d'éléments par page (Max : 25 éléments par page)
- Methode : GET
- Retour : Liste paginée de BrevetDTO

## Recherche détaillée de brevets
- URL : 
    - brevet/api/advancedSearch?publicationNumber=WO2017198756&publicationDate=23/11/2017&applicant=MAX-PLANCK-GESELLSCHAFT ZUR FÖRDERUNG DER WISSENSCHAFTEN E.V&inventor=ULLRICH Axel&cpc=A61K&geographicalScope=WO&keywords=insuline 
    
- Arguments : 
    - **publicationNumber** (optionnel) : Numéro de publication du brevet
    - **publicationDate** (optionnel) : Numéro de publication du brevet
    - **applicant** (optionnel) : Propriétaire du brevet
    - **inventor** (optionnel) : Inventeur du brevet 
    - **cpc** (optionnel) : Code CPC (Cooperative Patent Classfication)du brevet
    - **geographicalScope** (optionnel) : Code 2 lettres du pays du brevet
    - **keywords** (optionnel) : Mots clés à rechercher dans le titre & la description du brevet 
    - **page** (optionnel par défaut 0) : Numéro de la page (Attention commence par 0 donc 1 = 2e page)
    - **size** (optionnel : par defaut 25) : Nombre d'éléments par page (Max : 25 éléments par page)
- Methode : GET
- Retour : Liste paginée de BrevetDTO

## Récupérer le libellé d'un code CPC
- URL : 
    - brevet/api/search/getCpcLabel?cpc=E06
- Arguments : 
    - **cpc** : Code CPC dont on cherche le libellé
- Methode : GET
- Retour : String (libellé du code CPC). Ex Code : A63 => Libellé : SPORTS; GAMES; AMUSEMENTS



# WS Brevets

## Déposer un brevet sur la plateforme 	
- URL : brevet/api/deposerBrevet
- Arguments : 
    - **companyBrevetDTO** (obligatoire) : dto qui contient un objet companyDto et un objet brevetDto et une liste de mots-clés
        - **Exemple companyBrevetDTO** : {
	"company": {
		"id": 1,
		"name": "RATP",
		"sirenNumber": "012345678"
	},
	"brevet": {
		"datePublication": "20080506",
		"epodocNumber": "US7370203",
		"docdbNumber": "7370203",
		"kind": "B1",
		"pays": "US",
		"titres": [
			{
				"langue": "en",
				"titre": "Method for indivisibly modifying a plurality of sites in a microcircuit card non volatile memory, in particular a contactless card"
			}
		],
		"descriptions": [
			{
				"langue": "en",
				"description": "A method of connecting a card to a terminal including the following steps: a) on receiving corresponding respective commands from the terminal, it modifies the contents of the card memory by provisionally recording in the card memory each of said interdependent items of information without losing prior values corresponding to said items; and then b) the modifications are finalized either by all of them being confirmed or by all of them being discarded."
			}
		],
		"inventeurs": [
			{
				"sequence": "1",
				"dataFormat": "epodoc",
				"name": "GRIEU FRANCOIS [FR]"
			},
			{
				"sequence": "2",
				"dataFormat": "epodoc",
				"name": "DIDIER STEPHANE [FR]"
			},
			{
				"sequence": "1",
				"dataFormat": "original",
				"name": "GRIEU FRANCOIS,"
			},
			{
				"sequence": "2",
				"dataFormat": "original",
				"name": "DIDIER STEPHANE"
			}
		],
		"demandeurs": [
			{
				"sequence": "1",
				"dataFormat": "epodoc",
				"name": "REGIE AUTONOME TRANSPORTS [FR]"
			},
			{
				"sequence": "1",
				"dataFormat": "original",
				"name": "LA REGIE AUTONOME DES TRANSPORTS PARISIENS"
			}
		],
		"classifications": [
			{
				"sequence": "1",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "341",
				"classValue": "I"
			},
			{
				"sequence": "2",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "3672",
				"classValue": "I"
			},
			{
				"sequence": "3",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "3676",
				"classValue": "I"
			},
			{
				"sequence": "4",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "382",
				"classValue": "I"
			},
			{
				"sequence": "5",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "401",
				"classValue": "I"
			},
			{
				"sequence": "6",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "4097",
				"classValue": "I"
			},
			{
				"sequence": "7",
				"scheme": "CPC",
				"section": "G",
				"classe": "07",
				"subClass": "F",
				"mainGroup": "7",
				"subGroup": "082",
				"classValue": "I"
			},
			{
				"sequence": "8",
				"scheme": "CPC",
				"section": "G",
				"classe": "07",
				"subClass": "F",
				"mainGroup": "7",
				"subGroup": "0866",
				"classValue": "I"
			},
			{
				"sequence": "9",
				"scheme": "CPC",
				"section": "G",
				"classe": "07",
				"subClass": "F",
				"mainGroup": "7",
				"subGroup": "1008",
				"classValue": "I"
			},
			{
				"sequence": "10",
				"scheme": "CPC",
				"section": "G",
				"classe": "06",
				"subClass": "Q",
				"mainGroup": "20",
				"subGroup": "367",
				"classValue": "I"
			},
			{
				"sequence": "11",
				"scheme": "CPC",
				"section": "Y",
				"classe": "10",
				"subClass": "S",
				"mainGroup": "707",
				"subGroup": "99953",
				"classValue": "A"
			},
			{
				"sequence": "1",
				"scheme": "UC",
				"classSymbol": "705/64"
			},
			{
				"sequence": "2",
				"scheme": "UC",
				"classSymbol": "705/65"
			},
			{
				"sequence": "3",
				"scheme": "UC",
				"classSymbol": "705/66"
			},
			{
				"sequence": "4",
				"scheme": "UC",
				"classSymbol": "705/68"
			},
			{
				"sequence": "5",
				"scheme": "UC",
				"classSymbol": "705/75"
			},
			{
				"sequence": "6",
				"scheme": "UC",
				"classSymbol": "707/999.202"
			},
			{
				"sequence": "7",
				"scheme": "UC",
				"classSymbol": "713/172"
			},
			{
				"sequence": "8",
				"scheme": "UC",
				"classSymbol": "713/193"
			},
			{
				"sequence": "9",
				"scheme": "UC",
				"classSymbol": "714/15"
			},
			{
				"sequence": "10",
				"scheme": "UC",
				"classSymbol": "714/19"
			},
			{
				"sequence": "11",
				"scheme": "UC",
				"classSymbol": "714/21"
			},
			{
				"sequence": "12",
				"scheme": "UC",
				"classSymbol": "726/9"
			},
			{
				"sequence": "13",
				"scheme": "UC",
				"classSymbol": "726/20"
			},
			{
				"sequence": "14",
				"scheme": "UC",
				"classSymbol": "902/38"
			}
		]
	},
	"keywords": [
		"brevet",
		"patent",
		"lencify"
	]
}     
- Methode : POST
- Retour : Objet de type companyBrevetDTO


## Récupération des brevets publiés par un LencifyPartner	
- URL : 
    - brevet/api/company/2/publishedPatents : 2 ici correspond au companyId du LencifyPartner
- Arguments : 
    - **companyId** (obligatoire) : companyId du LencifyPartner pour lequel on recherche les brevets publiés    
- Methode : GET
- Retour : Liste paginée de BrevetDTO

## Accepter un brevet publié (statut EN_ATTENTE)
- URL : 
    - brevet/api/accepterBrevet    
- Arguments : 
    - **brevetDTO** (obligatoire) : le brevet à accepter
- Methode : POST
- Retour : Objet de type BrevetDTO

## Refuser un brevet publié (statut EN_ATTENTE)
- URL : 
    - brevet/api/refuserBrevet    
- Arguments : 
    - **brevetDTO** (obligatoire) : brevet à refuser
- Methode : POST
- Retour : Objet de type BrevetDTO

## Récupération des brevets avec le statut EN_ATTENTE
- URL : 
    - brevet/api/get/enAttente
- Arguments : Aucun        
- Methode : GET
- Retour : Liste paginée de BrevetDTO

## Supprimer un mot-clé d'un brevet
- URL : 
    - brevet/api/keyword/delete/{keywordId}
- Arguments : 
    - **keywordId** : l'id du mot-clé à supprimer       
- Methode : DELETE
- Retour : KeywordDTO

## Récupérer le détail d'un brevet par son id technique
- URL : 
    - brevet/api/get/{id}
- Arguments : 
    - **id** : Id du brevet qu'on souhaite récupérer
- Methode : GET
- Retour : BrevetDTO

## Récupérer le détail d'un brevet par son numéro epodoc
- URL : 
    - brevet/api/get/epodocNumber?epodocNumber={{epodocNumber}}
- Arguments : 
    - **epodocNumber** : epodocNumber du brevet qu'on souhaite récupérer
- Methode : GET
- Retour : BrevetDTO

## Récupérer les derniers brevets publiés sur la plateforme
- URL : 
    - brevet/api/get/brevetsRecents
- Arguments : Aucun
- Methode : GET
- Retour : List<BrevetDTO> : les 4 derniers brevets publiés sur la plateforme 

## Rechercher des brevets par entreprise et par mots clés (appel au moteur de suggestion)
- URL : 
    - brevet/api/search/searchByCompanyAndKeywords?companyName=RATP&keywords=transport
- Arguments : 
    - **companyName** : nom de l'entreprise
    - **keywords** : mots clés
- Methode : GET
- Retour : Objet de type ResultatRechercheBrevetDTO

## Recherche avcancée des brevets par entreprise et par mots clés (appel au moteur de suggestion)
- URL : 
    - brevet/api/search/byCompanyAndKeywordsAdvanced?companyName=test&keywords=&publicationNumber=&publicationDate=&applicant=&inventor=&cpc=&geographicalScope=
- Arguments : 
    - **companyName** (optionnel) : nom de l'entreprise
    - **keywords** (optionnel) : mots clés
    - **publicationNumber** (optionnel) : Numéro de publication du brevet
    - **publicationDate** (optionnel) : Numéro de publication du brevet
    - **applicant** (optionnel) : Propriétaire du brevet
    - **inventor** (optionnel) : Inventeur du brevet 
    - **cpc** (optionnel) : Code CPC (Cooperative Patent Classfication)du brevet
    - **geographicalScope** (optionnel) : Code 2 lettres du pays du brevet
    - **keywords** (optionnel) : Mots clés à rechercher dans le titre & la description du brevet 
    - **page** (optionnel par défaut 0) : Numéro de la page (Attention commence par 0 donc 1 = 2e page)
    - **size** (optionnel : par defaut 25) : Nombre d'éléments par page (Max : 25 éléments par page)
- Methode : GET
- Retour : Objet de type ResultatRechercheBrevetDTO

# WS Packs.

## Ajouter un nouveau pack
- URL : 
    - pack/api/add
- Arguments : 
    - **companyPackDto** (obligatoire) : dto qui contient un objet companyDto et un objet packDto
        - **Exemple companyPackDto** : 
	{
	"company":{
		"id" : 1,
		"name" : "RATP",
		"sirenNumber" : "123456789"
	},
	"pack":{        
        "label": "First pack",
        "keywords" : "#tag"        
    }
    }        
- Methode : POST
- Retour : PackDTO

## Modifier le label d'un pack
- URL : 
    - pack/api/update
- Arguments : 
    - **packDto** (obligatoire) : dto qui conttient les infos du pack et notamment le nouveau label
        - **Exemple PackDto** : 	
	{
        "id": 161,
        "label": "First pack",
        "companyId": 2
    }        
- Methode : PUT
- Retour : PackDTO

## Supprimer un pack
- URL : 
    - pack/api/delete/{packId}
- Arguments : 
    - **packId** : l'id du pack à supprimer       
- Methode : DELETE
- Retour : PackDTO

## Récupérer la liste paginée des packs d'une entreprise
- URL : 
    - pack/api/company/all?companyId=2
- Arguments : 
    - **companyId** (obligatoire) : l'id de l'entreprise
    - **page** (optionnel par défaut 0) : Numéro de la page (Attention commence par 0 donc 1 = 2e page)
    - **size** (optionnel : par defaut 25) : Nombre d'éléments par page (Max : 25 éléments par page)
- Methode : GET
- Retour : Liste paginée de PackDTO

## Récupérer la liste paginée des packs qui contiennent un brevet donné
- URL : 
    - pack/api/brevet?brevetId=2
- Arguments : 
    - **brevetId** (obligatoire) : l'id du brevet
    - **page** (optionnel par défaut 0) : Numéro de la page (Attention commence par 0 donc 1 = 2e page)
    - **size** (optionnel : par defaut 25) : Nombre d'éléments par page (Max : 25 éléments par page)
- Methode : GET
- Retour : Liste paginée de InfosPackDTO

## Ajouter un brevet à un pack
- URL : 
    - pack/api/ajouterBrevet
- Arguments : 
    - **PackBrevetDTO** (obligatoire) : dto qui contient un objet brevetDto et un objet packDto
        - **Exemple PackBrevetDTO** : 
	{
	"pack":{
        "id": 47,
        "label": "First pack",
        "companyId": 2
    },
	"brevet":{
            "datePublication": "19910616",
            "epodocNumber": "ES2019444",
            "docdbNumber": "2019444",
            "kind": "B3",
            "pays": "ES",
            "titres": [
                {
                    "titre": "EJE DELANTERO DIRIGIBLE PARA VEHICULOS."
                }
            ],
            "descriptions": [],
            "inventeurs": [
                {
                    "sequence": "1",
                    "dataFormat": "epodoc",
                    "name": "BAUSCH PAUL [DE]"
                },
                {
                    "sequence": "1",
                    "dataFormat": "original",
                    "name": "BAUSCH, PAUL"
                }
            ],
            "demandeurs": [
                {
                    "sequence": "1",
                    "dataFormat": "epodoc",
                    "name": "OPEL ADAM AG"
                },
                {
                    "sequence": "1",
                    "dataFormat": "original",
                    "name": "ADAM OPEL AKTIENGESELLSCHAFT"
                }
            ],
            "classifications": [
                {
                    "sequence": "1",
                    "scheme": "CPC",
                    "section": "B",
                    "classe": "62",
                    "subClass": "D",
                    "mainGroup": "7",
                    "subGroup": "20",
                    "classValue": "I"
                },
                {
                    "sequence": "2",
                    "scheme": "CPC",
                    "section": "B",
                    "classe": "62",
                    "subClass": "D",
                    "mainGroup": "9",
                    "subGroup": "00",
                    "classValue": "I"
                },
                {
                    "sequence": "3",
                    "scheme": "CPC",
                    "section": "B",
                    "classe": "60",
                    "subClass": "T",
                    "mainGroup": "8",
                    "subGroup": "246",
                    "classValue": "I"
                }
            ]
        }
           
}       
- Methode : POST
- Retour : PackDTO

## Retirer un brevet d'un pack
- URL : 
    - pack/api/retirerBrevet
- Arguments : 
    - **packBrevetDTO** (obligatoire) : dto qui contient un objet brevetDto et un objet packDto
        - **Exemple PackBrevetDTO** : 
	{
	"pack":{
        "id": 47,
        "label": "First pack",
        "companyId": 2
    },
	"brevet":{
            "datePublication": "19910616",
            "epodocNumber": "ES2019444",
            "docdbNumber": "2019444",
            "kind": "B3",
            "pays": "ES",
            "titres": [
                {
                    "titre": "EJE DELANTERO DIRIGIBLE PARA VEHICULOS."
                }
            ],
            "descriptions": [],
            "inventeurs": [
                {
                    "sequence": "1",
                    "dataFormat": "epodoc",
                    "name": "BAUSCH PAUL [DE]"
                },
                {
                    "sequence": "1",
                    "dataFormat": "original",
                    "name": "BAUSCH, PAUL"
                }
            ],
            "demandeurs": [
                {
                    "sequence": "1",
                    "dataFormat": "epodoc",
                    "name": "OPEL ADAM AG"
                },
                {
                    "sequence": "1",
                    "dataFormat": "original",
                    "name": "ADAM OPEL AKTIENGESELLSCHAFT"
                }
            ],
            "classifications": [
                {
                    "sequence": "1",
                    "scheme": "CPC",
                    "section": "B",
                    "classe": "62",
                    "subClass": "D",
                    "mainGroup": "7",
                    "subGroup": "20",
                    "classValue": "I"
                },
                {
                    "sequence": "2",
                    "scheme": "CPC",
                    "section": "B",
                    "classe": "62",
                    "subClass": "D",
                    "mainGroup": "9",
                    "subGroup": "00",
                    "classValue": "I"
                },
                {
                    "sequence": "3",
                    "scheme": "CPC",
                    "section": "B",
                    "classe": "60",
                    "subClass": "T",
                    "mainGroup": "8",
                    "subGroup": "246",
                    "classValue": "I"
                }
            ]
        }
           
}       
- Methode : POST
- Retour : PackDTO

## Créer le pack initial d'une entreprise (Avec le moteur de suggestion)
- URL : 
    - pack/api/initialPack
- Arguments : 
    - **companyDTO** (obligatoire) : dto qui contient les infos de la company propriétaire du pack initial 
        - **Exemple companyDTO** : 
	{
		"id" : 1,
		"name" : "RATP",
		"sirenNumber" : "123456789"		
	}        
- Methode : POST
- Retour : PackDTO (Actuellement le WS est bouchonné en attendant que le moteur de suggestion soit fonctionnel)

## Récupérer la liste paginée des brevets avec l'id du pack
- URL : 
    - brevet/api/getByPack/{packId}
- Arguments : 
    - **packId** (obligatoire) : id du pack
- Methode : GET
- Retour : Liste paginée de BrevetDTO 
