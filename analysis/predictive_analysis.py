import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

# Charger les données médicales
data = pd.read_csv('medical_records.csv')

# Préparer les données (features et labels)
X = data.drop('health_risk', axis=1)  # 'health_risk' est la colonne cible
y = data['health_risk']

# Convertir les colonnes catégorielles en numériques
X = pd.get_dummies(X, columns=['gender'])

# Diviser les données en ensembles d'entraînement et de test
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Entraîner un modèle de classification
model = RandomForestClassifier()
model.fit(X_train, y_train)

# Prédire sur l'ensemble de test
y_pred = model.predict(X_test)

# Évaluer le modèle
accuracy = accuracy_score(y_test, y_pred)
print(f"Accuracy: {accuracy}")

# Sauvegarder le modèle entraîné
import joblib
joblib.dump(model, 'saved_model.pkl')
