// Function to make a GET request to our API
function fetchCollegeStats() {
  // Define the URL of our API endpoint
  const apiUrl = 'http://localhost:8080/admin/getcolleges';

  // Make a GET request to the API
  fetch(apiUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then((data) => {
      displayResults(data);
    })
    .catch((error) => {
      console.error('There was a problem with the fetch operation:', error);
    });
}

// Function to display the college stats
function displayResults(data) {
  // Get form data from the URL query parameters
  const queryParams = new URLSearchParams(window.location.search);
  const Rank = queryParams.get("rank");
  const gender = queryParams.get("gender");
  const category = queryParams.get("category");
  const branch = queryParams.get("branch");

  const resultsDiv = document.getElementById("results");

  if (data.length === 0) {
      resultsDiv.innerHTML = '<p>No college data available.</p>';
  } else {
      
      data.forEach((college) => {
          
          const collegeCode = college.collegeCode;
          const collegeName = college.collegeName;
          const Cutoff = JSON.parse(college.branchCutoff);
          
          // Check if the branch exists in the JSON data

          if (Cutoff.hasOwnProperty(branch)) 
          {
            const branchData = Cutoff[branch];

            if(branchData.hasOwnProperty(category) && branchData[category] <= Rank) 
            {
              resultsDiv.innerHTML += `<h2>${collegeCode}.   ${collegeName}</h2>`;
              resultsDiv.innerHTML += `<h3>Branch: ${branch}</h3>`;
              resultsDiv.innerHTML += `${category}: ${branchData[category]}`;
              resultsDiv.innerHTML += `<hr>`;
            }
          } 
          else if(branch === "All")
          {
            let collegeAlloted = false;

            for(const branches in Cutoff) {
                const branchData = Cutoff[branches];
                if(branchData.hasOwnProperty(category) && branchData[category] <= Rank) 
                {
                  if(collegeAlloted === false) {
                    resultsDiv.innerHTML += `<h2>${collegeCode}.   ${collegeName}</h2>`;
                    collegeAlloted = true;
                  }
                  resultsDiv.innerHTML += `<h3>Branch: ${branches}</h3>`;
                  resultsDiv.innerHTML += `${category}: ${branchData[category]}`;
                  resultsDiv.innerHTML += `<hr>`;
                }
            }
          }
      })
      
      if(resultsDiv.innerHTML.trim() === ""){
        resultsDiv.innerHTML += `<h2>Sorry, we couldn't find any colleges that match your criteria.</h2>`;
        resultsDiv.innerHTML += `<p>Don't worry! There are many other great colleges out there. Try reaching out to colleges directly to see if they have any vacancies.</p>`
      }
  }

}

// Call the fetchCollegeStats function when the page loads
window.onload = fetchCollegeStats;
