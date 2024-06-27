/*!
* Start Bootstrap - Freelancer v7.0.5 (https://startbootstrap.com/theme/freelancer)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-freelancer/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            offset: 72,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });
    
    // script.js
function myFunction() {
    var menuCategories = document.getElementById("dropdown-menu");
    menuCategories.classList.toggle("hidden"); // Toggle between adding and removing the "hidden" class
  }
  function openDropdown() {
    document.getElementById("dropdown-menu").style.display = "block";
}

function closeDropdown() {
    document.getElementById("dropdown-menu").style.display = "none";
}

  

});
function toggleSearch() {
  var searchInputWrapper = document.getElementById("search_input_wrapper");
  searchInputWrapper.classList.toggle("show");
  if (searchInputWrapper.classList.contains("show")) {
      document.getElementById("search_input").focus();
  }
}

function hideSearch() {
  var searchInputWrapper = document.getElementById("search_input_wrapper");
  setTimeout(function() {
      searchInputWrapper.classList.remove("show");
  }, 200); // Wait for 200 milliseconds before hiding the search input wrapper
}