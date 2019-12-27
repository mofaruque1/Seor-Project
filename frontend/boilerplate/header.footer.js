var ddsHead = {
  stickyBanner: function () {

    $('.sticky-tfn-wrapper').css({
      position: 'fixed',
      transition: 'top 0.25s ease-in',

    });
    if ($(window).width() > 899) {
      $('.sticky-tfn-wrapper').css({
        top: '150px',
        right: 'calc((100% - ' + $('.dds-navbar-inner').width() + 'px) / 2)'

      });
    }
    else {
      $('.sticky-tfn-wrapper').css({
        right: 0,
        left: 0,
        top: '60px'
      });
      $('.top-promo-wrapper').css({ 'margin-top' : '30px' }); //to fix that banner issue
    }
    $('.wrapper-sticky').show();

    $('.how-to-get-it-label').on('click', function () {
      if ($(window).width() < 768) {
        if ($('.cta-list').hasClass('show-list') === false) {
          $('.cta-list').addClass('show-list');
        } else {
          $('.cta-list').removeClass('show-list');
        }
      }
    });



    //when accessibility is active on desktop

    $('.dds-axb-handle-inner').on('keyup', function (event) {
      $('.sticky-tfn-wrapper').css({
        top: '200px',
      });
    });

    $('.dds-axb-handle-inner a:first-child').on('keydown', function (event) {

      if (event.keyCode === 13) {
        $('.sticky-tfn-wrapper').css({
          top: '150px'
        });
      }
    })

    $('.dds_o-navLinkList__item').on('keyup', function (event) {
      $('.sticky-tfn-wrapper').css({
        top: '150px',
      });
    })


    $(document).click(function (e) {
      if ($(window).width() > 899) {
        $('.sticky-tfn-wrapper').css({
          top: '150px',
        });
      }
      else {
        $('.sticky-tfn-wrapper').css({
          top: '60px',
        });
      }
    })

    // when accessibility is active on mobile

    $('.dds-axb-handle-inner-mobile').on('keyup', function (event) {
      $('.sticky-tfn-wrapper').css({
        top: '110px',
      });
    });

    $('.dds-axb-handle-inner-mobile a:first-child').on('keydown', function (event) {

      if (event.keyCode === 13) {
        $('.sticky-tfn-wrapper').css({
          top: '60px'
        });
      }
    })

    $('#skipNavigationMobile').on('keyup', function (event) {
      $('.sticky-tfn-wrapper').css({
        top: '60px',
      });
    })

  },
  stickyBannerOnResize: function () {

    $('.sticky-tfn-wrapper').removeAttr('style');
    $('.sticky-tfn-wrapper').css({
      position: 'fixed',
      transition: 'top 0.25s ease-in',

    });
    if ($(window).width() > 899) {

      $('.sticky-tfn-wrapper').css({
        top: '150px',
        right: 'calc((100% - ' + $('.dds-navbar-inner').width() + 'px) / 2)'

      });
    }
    else {
      $('.sticky-tfn-wrapper').css({
        right: 0,
        left: 0,
        top: '60px'
      });
    }
  },

  noticeBanner: function () {
    if ($(window).width() > 899) {
      $('#banner-notice').css({ 'margin-top': '-5px', 'position': 'relative' });

    } else {
      $('#banner-notice').css({ 'margin-top': '-40px', 'position': 'relative' });
    }

  },

  accessibility: function () {
    // DESKTOP ACCESSIBLITY HANDLE
    $('.dds-axb-handle').focus();
    $('a[href="#skipNavigation"]').on('click', function (e) {
      e.preventDefault();
      $('.dds-axb-handle').focus();
      $('.dds-axb-handle-mobile').focus();

    })

    $('.dds_m-navLink.-axb')
      .focus(function () {
        $('body').addClass('axb-active');
      })
      .blur(function (e) {
        $('body').removeClass('axb-active');
      });

    // MOBILE ACCESSIBLITY HANDLE
    $('.dds-axb-handle-mobile').focus();
    $('.dds_m-mobileNavLink.-axb')
      .focus(function () {
        $('body').addClass('axb-active-mobile');
      })
      .blur(function (e) {
        $('body').removeClass('axb-active-mobile');
      });

    // DROPDOWNS
    $('[dropdown-section] > a').on('keypress', function (event) {
      if (event.keyCode === 13) {
        var $this = $(this);
        var $section = $(this).closest('[dropdown-section]');
        var $thisList = $section.find('[dropdown-list]');
        $this.trigger('click');

        var isActive = $(this).attr('aria-expanded') === 'true' ? true : false;
        $thisList.find('[tabindex]:first').focus();
        $thisList.find('a,input,button').attr('tabindex', isActive ? '0' : '-1');
        if (isActive) {
          $thisList.find('[tabindex="0"]:first').focus();
        } else {
          $this.focus();
        }
      }
    });

    // TOGGLE TABINDEX
    $('[dropdown-list]').on('keyup', function (event) {
      if (event.keyCode === 9) {
        var $sectionTrigger = $(this).closest('[dropdown-section]');
        var hasActive = $(this).hasClass('nav-list-opened');
        $(this)
          .find('[tabindex]')
          .attr('tabindex', hasActive ? '0' : '-1');
        if (!hasActive) {
          $($sectionTrigger)
            .find('[tabindex="0"]:first')
            .focus();
        }
      }
    });

    // HAMBURGER
    $('[data-navid]').on('keypress', function (event) {
      if (event.keyCode === 13) {
        var $this = $(this);
        $this.trigger('click');
        var thisCardId = $this.attr('data-navId');
        var $otherCards = $('[nav-id]');
        var $thisCard = $('[nav-id=' + thisCardId + ']');
        var isOpen = $thisCard.hasClass('navCard-open') ? true : false;

        // DISABLE TABS ON OTHER CARDS
        $otherCards.find('[tabindex]').attr('tabindex', '-1');

        // TARGET CARD
        $thisCard.find('[tabindex]').attr('tabindex', isOpen ? '0' : '-1');
        $thisCard.find('[tabindex="0"]:first').focus();
      }
    });

    // TOGGLE CARD TAB INDEX
    $('[nav-id]').on('keyup', function (event) {
      if (event.keyCode === 9) {
        // var countIndex = $('[tabindex]',this).length;
        // var posIndex = $('[tabindex]',this).index();
        console.log("hello");
      }
    });
  },
  menuToggle: function () {
    $('[dropdown-section] > a').on('click', function () {

      // for all the drop downs in desktop view
      var $this = $(this);
      var $section = $this.closest('[dropdown-section]');
      var $list = $section.find('[dropdown-list]');

      $('[dropdown-section]')
        .not($section)
        .removeClass('nav-list-active');
      $('[dropdown-list]')
        .not($list)
        .removeClass('nav-list-opened');
      $section.toggleClass('nav-list-active');
      $list.toggleClass('nav-list-opened');
      $this.attr('aria-expanded', $section.hasClass('nav-list-active') ? 'true' : 'false');



    });
    $('[dropdown-section]').on('mouseleave', function () {
      if ($(this).hasClass('nav-list-active')) {
        $('.dropdown-hide', this).trigger('click');
        // $('a', this).trigger('click');

      }
    });
  },
  mobileNav: function () {
    $('[data-navid]').on('click', function () {
      var $this = $(this);
      var classOpen = 'navCard-open';
      var classTrigger = 'navTrigger-active';

      var thisCardId = $this.attr('data-navId');
      var $cardActive = $('.' + classOpen);
      var $thisCard = $('[nav-id=' + thisCardId + ']');
      var $thisCardActive = $('[nav-id=' + thisCardId + '].' + classOpen);
      var $triggerActive = $('.navTrigger-active');
      var $thisTriggerActive = $('[data-navId=' + thisCardId + '].' + classTrigger);

      // CLOSE OTHER ACTIVE
      if ($cardActive) {
        $cardActive.removeClass(classOpen);
        $triggerActive.removeClass(classTrigger).attr('aria-expanded', 'false');
      }
      $this.addClass(classTrigger).attr('aria-expanded', 'true');
      $thisCard.addClass(classOpen);

      // CHECK STATE
      var checkOpen = $('.' + classOpen);
      if (checkOpen) {
        $('html').addClass('navCard-active');
      } else {
        $('html').removeClass('navCard-active');
      }

      // TOGGLE THIS
      if ($thisTriggerActive.length) {
        $thisCardActive.removeClass(classOpen);
        $thisTriggerActive.removeClass(classTrigger).attr('aria-expanded', 'false');
        $('html').removeClass('navCard-active');
      }
    });
  },
  headerState: function () {

    var is_consumer = (location.href.indexOf('consumer/') > -1);

    if (is_consumer) {
        $('.consumer-shop').addClass('-active');
    }
    var userState = ddsHead.getUserCurrentState();

    if (!userState.isUserAuthenticated && !userState.hasLastVerifiedUser) { //user is neither logged in nor has cookie
        $('.loginStates').removeClass('stateActive');
        $('.stateAnonymous').addClass('stateActive');
    }
    else if (userState.hasLastVerifiedUser && !userState.isUserAuthenticated) { //user has lastverified cookie stored and not logged in
        var signInAsText = $('.user-name-cookie:first').text();
        var truncateUserName = userState.verifiedUserValue.slice(0,userState.verifiedUserValue.indexOf("@")+1)+'...';
        $('.loginStates').removeClass('stateActive');
        $('.stateCookied').addClass('stateActive');
        $('.user-name-cookie').text(signInAsText + truncateUserName);
    }
    else if (userState.isUserAuthenticated) {
        $('.my-rogers-account').attr('href', '/web/totes/#/accountOverview');
        var userName = ddsHead.readCookie('pageUserName', false);
        var signedInUser = JSON.parse(window.localStorage.getItem('ute.rogers.signin.user'));
        if (userName === null) {
            userName = signedInUser.userName;
        }
        var truncateUserName = userName.slice(0,userName.indexOf("@")+1)+'...';
        $('.loginStates').removeClass('stateActive');
        $('.stateLoggedin').addClass('stateActive');
        $('.user-loggedin').text(truncateUserName);
    }
},
  getUserCurrentState: function(){
    return {
        isUserAuthenticated: (this.readCookie('SMSESSION', false) != null && this.readCookie('SMSESSION', false) !== 'LOGGEDOFF') ? true : false,
        hasLastVerifiedUser: (this.readCookie('rememberMe', false) != null) ? true : false,
        verifiedUserValue: decodeURIComponent(this.readCookie('rememberMe', false)),
        language: ($('html').attr('lang')) ? $('html').attr('lang') : (typeof this.readCookie('language', false) != 'undefined') ? this.readCookie('language', false) : 'en'
    };
},
  readCookie: function (name, forAttribute) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') c = c.substring(1, c.length);
      if (c.indexOf(nameEQ) == 0) {
        var decodedValue = decodeURIComponent(c.substring(nameEQ.length, c.length));
        return decodedValue;
      }
    }
    return null;
  },
  populateSearchQuickLinks: function () {

    const url = "http://www.qa01.rogers.com/cms/rogers/search_action_links/search_action_links.json";



    var lang = ddsHead.readCookie('language', false);
    if (lang === null) {
      //in case language cookie is not set
      lang = ddsHead.readCookie('cookielang', false);


    }


    // lang = "fr"; //to test

    $.get(url, function (res) {
      const length = res.quick_actions.length;
      for (let i = 0; i < length; i++) {
        // Desktop links
        var dQuickLinksAnchor = $('<a/>', {
          href: lang === "fr" ? res.quick_actions[i].url + "?setLanguage=fr" : res.quick_actions[i].url,
          class: 'dds_m-navLink -dropdown',
          tabindex: '-1',
          title: lang === "fr" ? res.quick_actions[i].fr_title : res.quick_actions[i].en_title,
          html: lang === "fr" ? res.quick_actions[i].fr_title : res.quick_actions[i].en_title
        });

        var dNewListItem = $('<li/>', {
          html: dQuickLinksAnchor,
          class: 'dds_o-headerNavDropdown__item'
        });
        $('.dQuickLinks').append(dNewListItem);

        // Mobile links
        var mQuickLinksAnchor = $('<a/>', {
          href: lang === "fr" ? res.quick_actions[i].url + "?setLanguage=fr" : res.quick_actions[i].url,
          class: 'dds_m-mobileNavLink',
          tabindex: '-1',
          title: lang === "fr" ? res.quick_actions[i].fr_title : res.quick_actions[i].en_title,
          html: lang === "fr" ? res.quick_actions[i].fr_title : res.quick_actions[i].en_title
        });

        var mNewListItem = $('<li/>', {
          html: mQuickLinksAnchor,
          class: 'dds_o-mobileNavDropdown__item'
        });
        $('.mQuickLinks').append(mNewListItem);
      }
    });

  },
  consumerSearch: function () {

    var searchTerm;
    $('header form').on('submit', function (e) {
      e.preventDefault();
      searchTerm = $('input', this).val();
      // window.location = 'https://www.rogers.com/customer/search?searchTerm=' + searchTerm;
      window.location = '/customer/search?searchTerm=' + searchTerm;

    });
  },
  resizeHandler: function () {
    ddsHead.stickyBannerOnResize();
    // ddsHead.noticeBanner();
  },
  initFunctions: {
    docReady: function () {
      ddsHead.menuToggle();
      ddsHead.mobileNav();
      ddsHead.accessibility();
      ddsHead.headerState();
      ddsHead.consumerSearch();
      ddsHead.populateSearchQuickLinks();
      ddsHead.stickyBanner();
      //ddsHead.noticeBanner();

    }
  }
};

// INIT
$(function () {
  ddsHead.initFunctions.docReady();
  $(window).resize(ddsHead.resizeHandler);

});










/*


initializer: function() {
  if (typeof $('header').html() !== 'undefined') {
    ddsHead.initFunctions.docReady();
    $(window).resize(ddsHead.resizeHandler);
  }
  else {
    setTimeout(function () {
      ddsHead.initializer();
    }, 20);
  }
},
initFunctions: {
  docReady: function () {
    ddsHead.menuToggle();
    ddsHead.mobileNav();
    ddsHead.accessibility();
    ddsHead.headerState();
    ddsHead.consumerSearch();
    ddsHead.populateSearchQuickLinks();
    ddsHead.stickyBanner();

  }
}
};

// INIT
$(function () {
  ddsHead.initializer();

});

*/