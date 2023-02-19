# WS gestion-profil-mcs

## Recupération du profil d'un user	
- URL : 
    - user/getInformation
- Arguments : Pas d'argument    
- Methode : GET
- Retour : Objet de type LencifyUserProfileDTO


## Modifier les informations de profil d'un user
- URL : 
    - user/updateProfile     
- Arguments : 
    - **lencifyUserProfileDTO** : LencifyUserProfileDTO
- Methode : PUT
- Retour : Objet de type LencifyUserProfileDTO

## Ajouter un produit
- URL : 
    - product/add     
- Arguments : 
    - **productDTO** : ProductDTO
- Methode : POST
- Retour : Objet de type ProductDTO

## Mettre à jour un produit
- URL : 
    - product/update     
- Arguments : 
    - **productDTO** : ProductDTO
- Methode : PUT
- Retour : Objet de type ProductDTO

## Supprimer un mot clé d'un produit
- URL : 
    - product/keyword/delete/{keywordId}     
- Arguments : 
    - **keywordId** : Id du mot clé qu'on veut supprimer
- Methode : DELETE
- Retour : void


