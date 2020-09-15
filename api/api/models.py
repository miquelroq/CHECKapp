from django.db import models


class Report(models.Model):
    """
    Report Class
    This is the class that will be serialized and returned whenever
    a GET to the main endpoint is made

    It encapsulates the relevant data for each of the BaseValuesHandler parameters
    """
    
    BPM = models.IntegerField() # Calculated from ECG
    DIFF = models.IntegerField() # diff(max, min) from ECG
    BREATH = models.IntegerField() # Calculated from ECG
    ALPHA = models.IntegerField() # EEG 
    BETA = models.IntegerField() # EEG
    DELTA = models.IntegerField() # EEG
    THETA = models.IntegerField() # EEG

    def __str__(self):
        return "Cool Report"
