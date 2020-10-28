# Alarm-doc

*Project developed in the Summer Course "IoT apps in the health field" by IPV, supported by a research schoolarship by FCT.*

## POST IT NOTES 
 - BITalino protocol and utilities meant to write to/read from BITalino devices. <https://github.com/BITalinoWorld/java-sdk>
 - BITalino physical link (i.e. Bluetooth connection) management  <https://github.com/BITalinoWorld/revolution-android-api>

## Ideas (possibly implement later)
- popup/notification of danger of having COVID based on the datasets of people with COVID.
- COVID probability functionality.

## Planning

### Subject data worth knowing (by method):
 
- **Formulário inicial**
  - Gender                       
  - Age                          
  - Height                       
  - Weight                       
  - Lifestyle   
  - Previous diseases (dropdown)
  
- **Sensor ECG**
  - Heart rate                   
  - Breath rate    
 
- **Sensor acelerómetro**
  - Last time/how often fainted    
  
- **Boolean Question**
  - Chills   
  - Fatigue      
  
- **Sensor EEG**  
  - Migraine / Headache          
                  
- **Sensor EMG**
  - Muscle Pain    
  - <https://ieeexplore.ieee.org/document/4147460>
            
- **Sensor temperatura**
  - Temperature     

- **Sensor ???**
  - Blood pressure               
  
- **don't know how- maybe develop a simple test in the app**
  - Reaction time                
  
- **not viable**               
   - Glicemia
  
### Diseases we aim to predict
 
  - Fever
  - Tachycardia ( > 100 bpm)
  - Bradycardia ( < 60 bpm)
  - Epylepsy
  - Check the ones in datasets
  
  
### Lifecycle

 - Recolha de dados (sensores + formulário)
 - Cálculo e atribuição de determinados valores numa espécie de matriz (ex: matriz FIFA - *see below*)
 
   <img src="https://github.com/miquelroq/health-app/blob/master/files/resources/radar_graph" alt="graph" width="300"/>
 - Interseção com a "matriz" de determinadas doenças
 - Diagnóstico


## Useful links

### Software
- <https://www.instructables.com/id/Android-Bluetooth-Control-LED-Part-2/> 

- <https://medium.com/@jeyahariprakash/android-design-patterns-3b69e649aad6> (**Design patterns**)

- <https://bitalino.com/en/learn/get-started> (**Bitalino**)

- <https://medium.com/google-developers/about-10-things-you-probably-didn-t-know-you-could-do-in-android-studio-de231071b375>

- <https://medium.com/@mobindustry/how-to-quickly-implement-beautiful-charts-in-your-android-app-cf4caf050772> (**CHARTS**)

- <https://developer.android.com/reference/android/speech/tts/TextToSpeech> (**Text To Speech**)

- <https://developer.android.com/reference/kotlin/android/location/LocationManager> (**Location Manager**)

- **!!!** <https://github.com/leanderme/sytora> **!!! -> CHECK THIS REALLY IMPORTANT**


---

- **<https://forum.bitalino.com/viewtopic.php?t=129>** **!!! -> BITALINO SIGNAL** 
- <https://bitalino.com/en/development/apis>

---


### Health
- <https://www.medis.pt/covid-19-symptom-checker/> 
  - <https://ouraring.com/ucsf-tempredict-study>
  - <https://www.biofourmis.com/>
  
- **!!!** <https://detectstudy.org/> **!!! -> CHECK THIS REALLY IMPORTANT**

- <https://www.datarevenue.com/en-blog/machine-learning-covid-19> (**Machine learning**)


### DATASETS

***seem the best ones***

 - <https://www.kaggle.com/plarmuseau/sdsort/home>
 
 - <https://www.kaggle.com/plarmuseau/primer>
 
 - https://www.kaggle.com/plarmuseau/symptom-disease-recommender

***others***

 - <https://hpo.jax.org/app/download/annotation> 
 
 - <https://www.datasciencecentral.com/m/blogpost?id=6448529%3ABlogPost%3A376308> 
 
 - <https://medium.com/@ODSC/15-open-datasets-for-healthcare-830b19980d9>
 
 - <https://www.kaggle.com/itachi9604/disease-symptom-description-dataset?select=dataset.csv>


## Authors

Miguel Roque: <miguelroque99@tecnico.ulisboa.pt>

Tiago Melo: <tiagomelo@ua.pt>
