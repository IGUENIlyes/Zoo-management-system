const API_BASE = "/api/animals";

const els = {
  totalAnimals: document.getElementById("totalAnimals"),
  dailyFood: document.getElementById("dailyFood"),
  animalsGrid: document.getElementById("animalsGrid"),
  message: document.getElementById("message"),
  refreshBtn: document.getElementById("refreshBtn"),
  createForm: document.getElementById("createForm"),
  createType: document.getElementById("createType"),
  typeFields: document.getElementById("typeFields"),
  updateForm: document.getElementById("updateForm"),
  updateId: document.getElementById("updateId"),
  filterType: document.getElementById("filterType"),
  filterSpecies: document.getElementById("filterSpecies"),
  filterName: document.getElementById("filterName"),
  applyFiltersBtn: document.getElementById("applyFiltersBtn"),
  clearFiltersBtn: document.getElementById("clearFiltersBtn"),
  deleteByName: document.getElementById("deleteByName"),
  deleteByNameBtn: document.getElementById("deleteByNameBtn"),
  loadStatsBtn: document.getElementById("loadStatsBtn"),
  statsByCategory: document.getElementById("statsByCategory"),
  statsAverageAge: document.getElementById("statsAverageAge"),
  statsAverageWeight: document.getElementById("statsAverageWeight")
};

function showMessage(text, isError = false) {
  els.message.textContent = text || "";
  els.message.className = isError ? "message error" : "message";
}

function toNumber(value) {
  return value === "" || value == null ? null : Number(value);
}

function toBoolean(value) {
  if (value === "true") return true;
  if (value === "false") return false;
  return null;
}

function fieldTemplateForType(type) {
  const t = (type || "").toLowerCase();

  if (t === "mammifere") {
    return `
      <div><label>Produit lait *</label><select name="produitLait" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Carnivore *</label><select name="isCarnivore" required><option value="true">Oui</option><option value="false">Non</option></select></div>
    `;
  }

  if (t === "oiseau") {
    return `
      <div><label>Envergure (m) *</label><input name="envergure" type="number" step="0.1" min="0.1" required /></div>
      <div><label>Vole *</label><select name="vole" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Type de bec *</label><input name="beaktype" required /></div>
    `;
  }

  if (t === "poisson") {
    return `
      <div><label>Type d'eau *</label><select name="waterType" required><option value="eau douce">eau douce</option><option value="eau salée">eau salée</option></select></div>
      <div><label>Longueur (m) *</label><input name="longueur" type="number" step="0.1" min="0.1" required /></div>
      <div><label>Prédateur *</label><select name="isPredator" required><option value="true">Oui</option><option value="false">Non</option></select></div>
    `;
  }

  if (t === "reptile") {
    return `
      <div><label>Venimeux *</label><select name="venimeux" id="venimeuxSelect" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Mue *</label><select name="mue" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Température optimale (°C) *</label><input name="temperatureOptimale" type="number" step="0.1" min="45" required /></div>
      <div id="typeVeninWrap"><label>Type venin</label><input name="typeVenin" /></div>
    `;
  }

  if (t === "amphibien") {
    return `
      <div><label>Vit sur terre *</label><select name="vivresurterre" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Vit dans l'eau *</label><select name="vivredansleau" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Longueur (m) *</label><input name="longueur" type="number" step="0.1" min="0.1" required /></div>
    `;
  }

  if (t === "invertebre") {
    return `
      <div><label>Exosquelette *</label><select name="hasExoskeleton" required><option value="true">Oui</option><option value="false">Non</option></select></div>
      <div><label>Nombre de pattes *</label><input name="numberOfLegs" type="number" min="0" required /></div>
    `;
  }

  return "";
}

function renderTypeFields() {
  const type = els.createType.value;
  els.typeFields.innerHTML = fieldTemplateForType(type);

  const venimeux = document.getElementById("venimeuxSelect");
  if (venimeux) {
    const toggle = () => {
      const wrap = document.getElementById("typeVeninWrap");
      const input = wrap ? wrap.querySelector("input[name='typeVenin']") : null;
      const required = venimeux.value === "true";
      if (input) input.required = required;
      if (wrap) wrap.style.display = required ? "block" : "none";
    };
    venimeux.addEventListener("change", toggle);
    toggle();
  }
}

function buildCreatePayload(form) {
  const fd = new FormData(form);
  const payload = {
    name: fd.get("name"),
    age: Number(fd.get("age")),
    poids: Number(fd.get("poids")),
    species: fd.get("species"),
    type: fd.get("type"),
    habitat: fd.get("habitat")
  };

  const type = (payload.type || "").toLowerCase();

  if (type === "mammifere") {
    payload.produitLait = toBoolean(fd.get("produitLait"));
    payload.isCarnivore = toBoolean(fd.get("isCarnivore"));
  }
  if (type === "oiseau") {
    payload.envergure = toNumber(fd.get("envergure"));
    payload.vole = toBoolean(fd.get("vole"));
    payload.beaktype = fd.get("beaktype");
  }
  if (type === "poisson") {
    payload.waterType = fd.get("waterType");
    payload.longueur = toNumber(fd.get("longueur"));
    payload.isPredator = toBoolean(fd.get("isPredator"));
  }
  if (type === "reptile") {
    payload.venimeux = toBoolean(fd.get("venimeux"));
    payload.mue = toBoolean(fd.get("mue"));
    payload.temperatureOptimale = toNumber(fd.get("temperatureOptimale"));
    payload.typeVenin = fd.get("typeVenin") || null;
  }
  if (type === "amphibien") {
    payload.vivresurterre = toBoolean(fd.get("vivresurterre"));
    payload.vivredansleau = toBoolean(fd.get("vivredansleau"));
    payload.longueur = toNumber(fd.get("longueur"));
  }
  if (type === "invertebre") {
    payload.hasExoskeleton = toBoolean(fd.get("hasExoskeleton"));
    payload.numberOfLegs = Number(fd.get("numberOfLegs"));
  }

  return payload;
}

async function api(url, options = {}) {
  const res = await fetch(url, {
    headers: { "Content-Type": "application/json" },
    ...options
  });
  const json = await res.json();
  if (!res.ok || json.success === false) {
    throw new Error(json.message || "Erreur API");
  }
  return json;
}

function createAnimalCard(animal) {
  const div = document.createElement("article");
  div.className = "animal-card";
  div.innerHTML = `
    <h3>${animal.name} <small>${animal.type}</small></h3>
    <p><strong>ID:</strong> ${animal.id}</p>
    <p><strong>Espèce:</strong> ${animal.species}</p>
    <p><strong>Âge:</strong> ${animal.age} ans</p>
    <p><strong>Poids:</strong> ${animal.poids} kg</p>
    <p><strong>Habitat:</strong> ${animal.habitat}</p>
    <p><strong>Nourriture/jour:</strong> ${animal.dailyFood.toFixed(2)} kg</p>
    <div class="card-actions">
      <button class="btn btn-secondary edit-btn">Éditer</button>
      <button class="btn danger delete-btn">Supprimer</button>
    </div>
  `;

  div.querySelector(".edit-btn").addEventListener("click", () => {
    const f = els.updateForm;
    f.name.value = animal.name;
    f.age.value = animal.age;
    f.poids.value = animal.poids;
    f.species.value = animal.species;
    f.type.value = animal.type;
    f.habitat.value = animal.habitat;
    els.updateId.value = animal.id;
    window.scrollTo({ top: f.offsetTop - 30, behavior: "smooth" });
  });

  div.querySelector(".delete-btn").addEventListener("click", async () => {
    if (!confirm(`Supprimer ${animal.name} ?`)) return;
    try {
      const r = await api(`${API_BASE}/${animal.id}`, { method: "DELETE" });
      showMessage(r.message);
      await loadAnimals();
      await loadDailyFood();
    } catch (e) {
      showMessage(e.message, true);
    }
  });

  return div;
}

async function loadDailyFood() {
  const r = await api(`${API_BASE}/daily-food`);
  els.dailyFood.textContent = `${Number(r.data).toFixed(2)} kg`;
}

async function loadAnimals() {
  const r = await api(API_BASE);
  const animals = r.data || [];
  els.totalAnimals.textContent = animals.length;
  els.animalsGrid.innerHTML = "";
  animals.forEach(a => els.animalsGrid.appendChild(createAnimalCard(a)));
}

async function applyFilters() {
  const type = els.filterType.value.trim();
  const species = els.filterSpecies.value.trim();
  const name = els.filterName.value.trim();

  try {
    let data = [];

    if (name) {
      data = (await api(`${API_BASE}/name/${encodeURIComponent(name)}`)).data || [];
    } else if (type && !species) {
      data = (await api(`${API_BASE}/type/${encodeURIComponent(type)}`)).data || [];
    } else if (!type && species) {
      data = (await api(`${API_BASE}/species/${encodeURIComponent(species)}`)).data || [];
    } else if (type && species) {
      const all = (await api(API_BASE)).data || [];
      data = all.filter(a =>
        a.type.toLowerCase() === type.toLowerCase() &&
        a.species.toLowerCase() === species.toLowerCase()
      );
    } else {
      data = (await api(API_BASE)).data || [];
    }

    els.totalAnimals.textContent = data.length;
    els.animalsGrid.innerHTML = "";
    data.forEach(a => els.animalsGrid.appendChild(createAnimalCard(a)));
    showMessage(`Filtre appliqué: ${data.length} résultat(s).`);
    
    // Scroll automatique vers la liste des animaux
    setTimeout(() => {
      els.animalsGrid.parentElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }, 100);
  } catch (e) {
    showMessage(e.message, true);
  }
}

async function loadStats() {
  try {
    const [cat, age, weight] = await Promise.all([
      api(`${API_BASE}/stats/by-category`),
      api(`${API_BASE}/stats/average-age`),
      api(`${API_BASE}/stats/average-weight`)
    ]);

    els.statsByCategory.innerHTML = Object.entries(cat.data).map(([k, v]) => 
      `<p><strong>${k}:</strong> ${v}</p>`
    ).join("");

    els.statsAverageAge.innerHTML = Object.entries(age.data).map(([k, v]) => 
      `<p><strong>${k}:</strong> ${v.toFixed(1)} ans</p>`
    ).join("");

    els.statsAverageWeight.innerHTML = Object.entries(weight.data).map(([k, v]) => 
      `<p><strong>${k}:</strong> ${v.toFixed(2)} kg</p>`
    ).join("");

    showMessage("Statistiques chargées avec succès.");
  } catch (e) {
    showMessage(e.message, true);
  }
}

els.createType.addEventListener("change", renderTypeFields);

els.createForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  try {
    const payload = buildCreatePayload(els.createForm);
    const r = await api(API_BASE, {
      method: "POST",
      body: JSON.stringify(payload)
    });
    showMessage(r.message);
    els.createForm.reset();
    els.typeFields.innerHTML = "";
    await loadAnimals();
    await loadDailyFood();
  } catch (err) {
    showMessage(err.message, true);
  }
});

els.updateForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  if (!els.updateId.value) {
    showMessage("Choisissez d'abord un animal à éditer.", true);
    return;
  }

  const payload = {
    name: els.updateForm.name.value,
    age: Number(els.updateForm.age.value),
    poids: Number(els.updateForm.poids.value),
    species: els.updateForm.species.value,
    type: els.updateForm.type.value,
    habitat: els.updateForm.habitat.value
  };

  try {
    const r = await api(`${API_BASE}/${els.updateId.value}`, {
      method: "PUT",
      body: JSON.stringify(payload)
    });
    showMessage(r.message);
    els.updateForm.reset();
    els.updateId.value = "";
    await loadAnimals();
    await loadDailyFood();
  } catch (err) {
    showMessage(err.message, true);
  }
});

els.deleteByNameBtn.addEventListener("click", async () => {
  const name = els.deleteByName.value.trim();
  if (!name) {
    showMessage("Entrez un nom valide.", true);
    return;
  }
  if (!confirm(`Supprimer l'animal nommé '${name}' ?`)) return;

  try {
    const r = await api(`${API_BASE}/name/${encodeURIComponent(name)}`, { method: "DELETE" });
    showMessage(r.message);
    els.deleteByName.value = "";
    await loadAnimals();
    await loadDailyFood();
  } catch (e) {
    showMessage(e.message, true);
  }
});

els.applyFiltersBtn.addEventListener("click", applyFilters);

els.clearFiltersBtn.addEventListener("click", async () => {
  els.filterType.value = "";
  els.filterSpecies.value = "";
  els.filterName.value = "";
  showMessage("");
  await loadAnimals();
});

els.loadStatsBtn.addEventListener("click", loadStats);

els.refreshBtn.addEventListener("click", async () => {
  showMessage("Rafraîchissement...");
  await loadAnimals();
  await loadDailyFood();
  showMessage("Liste mise à jour.");
});

(async function init() {
  try {
    await loadAnimals();
    await loadDailyFood();
  } catch (e) {
    showMessage(e.message, true);
  }
})();