from django.db import models


class Report(models.Model):
    """
    Report Class
    
    This is the class that will be serialized and returned whenever
    a GET to the main endpoint is made

    It encapsulates the relevant data for each of the BaseValuesHandler parameters
    """
    
    BPM = models.IntegerField()     # Calculated from ECG
    DIFF = models.IntegerField()    # diff(max, min) from ECG
    BREATH = models.IntegerField()  # Calculated from ECG

    ALPHA = models.IntegerField() # EEG 
    BETA = models.IntegerField()  # EEG
    DELTA = models.IntegerField() # EEG
    THETA = models.IntegerField() # EEG
    GAMMA = models.IntegerField() # EEG

    def __init__(self, bpm, diff, breath, alpha, beta, delta, theta, gamma):
        self.BPM = bpm
        self.DIFF = diff
        self.BREATH = breath
        self.ALPHA = alpha
        self.BETA = beta
        self.DELTA = delta
        self.THETA = theta
        self.GAMMA = gamma

    def __str__(self):
        return "Cool Report"
